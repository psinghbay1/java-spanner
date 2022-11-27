/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.spanner.connection;

import com.google.cloud.Date;
import com.google.cloud.Timestamp;
import com.google.cloud.spanner.ErrorCode;
import com.google.cloud.spanner.Mutation;
import com.google.cloud.spanner.Mutation.WriteBuilder;
import com.google.cloud.spanner.SpannerException;
import com.google.cloud.spanner.SpannerExceptionFactory;
import com.google.cloud.spanner.Statement;
import com.google.cloud.spanner.Value;
import com.google.cloud.spanner.connection.SimpleParser.QuotedString;
import com.google.cloud.spanner.connection.SimpleParser.TableOrIndexName;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.protobuf.NullValue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

class DmlToMutationsConverter {

  static List<Mutation> convert(Statement statement) {
    SimpleParser parser = new SimpleParser(statement.getSql());
    if (parser.eatKeyword("insert")) {
      return convertInsert(statement);
    } else if (parser.eatKeyword("update")) {
      return convertUpdate(statement);
    } else if (parser.eatKeyword("delete")) {
      return convertDelete(statement);
    } else {
      throw SpannerExceptionFactory.newSpannerException(
          ErrorCode.INVALID_ARGUMENT, "Statement cannot be converted to a mutation: " + statement);
    }
  }

  static List<Mutation> convertInsert(Statement statement) {
    SimpleParser parser = new SimpleParser(statement.getSql());
    if (!parser.eatKeyword("insert")) {
      throw SpannerExceptionFactory.newSpannerException(
          ErrorCode.INVALID_ARGUMENT, "Statement is not an insert statement: " + statement);
    }
    parser.eatKeyword("into");
    TableOrIndexName table = parser.readTableOrIndexName();
    if (table == null) {
      throw SpannerExceptionFactory.newSpannerException(
          ErrorCode.INVALID_ARGUMENT, "Invalid table name in insert statement: " + statement);
    }
    if (!parser.eatToken("(")) {
      throw SpannerExceptionFactory.newSpannerException(
          ErrorCode.INVALID_ARGUMENT, "Missing opening parentheses for columns list: " + statement);
    }
    List<String> columnsList = parser.parseExpressionList();
    if (!parser.eatToken(")")) {
      throw SpannerExceptionFactory.newSpannerException(
          ErrorCode.INVALID_ARGUMENT, "Missing opening parentheses for columns list: " + statement);
    }
    if (parser.eatKeyword("select")) {
      throw SpannerExceptionFactory.newSpannerException(
          ErrorCode.INVALID_ARGUMENT,
          "Insert statements with a select query cannot be converted to mutations: " + statement);
    }
    if (!parser.eatKeyword("values")) {
      throw SpannerExceptionFactory.newSpannerException(
          ErrorCode.INVALID_ARGUMENT, "Missing 'values' keyword in insert statement: " + statement);
    }
    List<List<String>> rows = new ArrayList<>();
    while (parser.eatToken("(")) {
      int posBeforeValues = parser.getPos();
      List<String> row = parser.parseExpressionList();
      if (row == null
          || row.isEmpty()
          || !parser.eatToken(")")
          || row.size() != columnsList.size()) {
        throw SpannerExceptionFactory.newSpannerException(
            ErrorCode.INVALID_ARGUMENT,
            String.format(
                "Invalid values list as position %d: %s",
                posBeforeValues, parser.getSql().substring(posBeforeValues, parser.getPos())));
      }
      rows.add(row);
      if (!parser.eatToken(",")) {
        break;
      }
    }
    if (rows.isEmpty()) {
      throw SpannerExceptionFactory.newSpannerException(
          ErrorCode.INVALID_ARGUMENT, "Missing values list in insert statement");
    }
    ImmutableList.Builder<Mutation> mutationsBuilder = ImmutableList.builder();
    for (List<String> row : rows) {
      WriteBuilder insertBuilder = Mutation.newInsertBuilder(table.getUnquotedQualifiedName());
      for (int columnIndex = 0; columnIndex < columnsList.size(); columnIndex++) {
        String valueExpression = row.get(columnIndex).trim();
        if (valueExpression.equals("")) {
          throw SpannerExceptionFactory.newSpannerException(
              ErrorCode.INVALID_ARGUMENT,
              String.format(
                  "Empty value for column %s in value list %s",
                  columnsList.get(columnIndex), String.join(", ", row)));
        }
        insertBuilder
            .set(columnsList.get(columnIndex))
            .to(convertExpressionToValue(statement, valueExpression));
      }
      mutationsBuilder.add(insertBuilder.build());
    }
    return mutationsBuilder.build();
  }

  static List<Mutation> convertUpdate(Statement statement) {
    SimpleParser parser = new SimpleParser(statement.getSql());
    if (!parser.eatKeyword("update")) {
      throw SpannerExceptionFactory.newSpannerException(
          ErrorCode.INVALID_ARGUMENT, "Statement is not an update statement: " + statement);
    }
    TableOrIndexName table = parser.readTableOrIndexName();
    if (table == null) {
      throw SpannerExceptionFactory.newSpannerException(
          ErrorCode.INVALID_ARGUMENT, "Invalid table name in update statement: " + statement);
    }
    parser.skipTableHintExpression();
    String alias = table.getUnquotedName();
    boolean mustHaveAlias = parser.eatKeyword("as");
    if (mustHaveAlias || !parser.peekKeyword("set")) {
      alias = parser.readIdentifierPart();
      if (alias == null) {
        throw SpannerExceptionFactory.newSpannerException(
            ErrorCode.INVALID_ARGUMENT, "Invalid or missing table alias: " + statement);
      }
    }
    if (!parser.eatKeyword("set")) {
      throw SpannerExceptionFactory.newSpannerException(
          ErrorCode.INVALID_ARGUMENT, "Missing keyword 'set' in update statement: " + statement);
    }
    List<String> assignmentsList = parser.parseExpressionListUntilKeyword("where", true);
    if (assignmentsList == null || assignmentsList.isEmpty()) {
      throw SpannerExceptionFactory.newSpannerException(
          ErrorCode.INVALID_ARGUMENT, "Empty assignment list in update statement: " + statement);
    }
    if (!parser.eatKeyword("where")) {
      throw SpannerExceptionFactory.newSpannerException(
          ErrorCode.INVALID_ARGUMENT,
          "Update statements without a WHERE-clause are not supported for mutations: " + statement);
    }
    ImmutableMap<String, Value> assignments =
        convertAssignmentList(assignmentsList, statement, alias);
    ImmutableMap<String, Value> whereClauses = parseWhereClauses(parser, statement, alias);

    WriteBuilder updateBuilder = Mutation.newUpdateBuilder(table.getUnquotedQualifiedName());
    for (Entry<String, Value> entry :
        Iterables.concat(whereClauses.entrySet(), assignments.entrySet())) {
      updateBuilder.set(entry.getKey()).to(entry.getValue());
    }
    return ImmutableList.of(updateBuilder.build());
  }

  static List<Mutation> convertDelete(Statement statement) {
    return ImmutableList.of();
  }

  static ImmutableMap<String, Value> convertAssignmentList(
      List<String> assignments, Statement statement, String tableNameOrAlias) {
    ImmutableMap.Builder<String, Value> assignmentsBuilder = ImmutableMap.builder();
    for (String assignment : assignments) {
      SimpleParser parser = new SimpleParser(assignment);
      TableOrIndexName columnName = parser.readTableOrIndexName();
      if (columnName == null) {
        throw SpannerExceptionFactory.newSpannerException(
            ErrorCode.INVALID_ARGUMENT,
            "Invalid or missing column name in assignment: " + statement);
      }
      if (columnName.schema != null
          && !columnName.getUnquotedSchema().equalsIgnoreCase(tableNameOrAlias)) {
        throw SpannerExceptionFactory.newSpannerException(
            ErrorCode.INVALID_ARGUMENT, "Invalid table or alias name in assignment: " + columnName);
      }
      if (!parser.eatToken("=")) {
        throw createInvalidAssignmentException(statement);
      }
      String valueExpression = parser.getSql().substring(parser.getPos());
      assignmentsBuilder.put(columnName.name, convertExpressionToValue(statement, valueExpression));
    }
    return assignmentsBuilder.build();
  }

  static ImmutableMap<String, Value> parseWhereClauses(
      SimpleParser parser, Statement statement, String tableNameOrAlias) {
    ImmutableMap.Builder<String, Value> whereClausesBuilder = ImmutableMap.builder();
    while (true) {
      TableOrIndexName columnName = parser.readTableOrIndexName();
      if (columnName == null) {
        throw createInvalidWhereClauseException(statement);
      }
      if (columnName.schema != null
          && !columnName.getUnquotedSchema().equalsIgnoreCase(tableNameOrAlias)) {
        throw SpannerExceptionFactory.newSpannerException(
            ErrorCode.INVALID_ARGUMENT,
            "Invalid table or alias name in where clause: " + columnName);
      }
      if (!parser.eatToken("=")) {
        throw createInvalidWhereClauseException(statement);
      }
      String valueExpression =
          parser.parseExpressionUntilKeyword(ImmutableList.of("and"), true, true);
      whereClausesBuilder.put(
          columnName.name, convertExpressionToValue(statement, valueExpression));
      if (parser.hasMoreTokens()) {
        parser.eatKeyword("and");
        if (!parser.hasMoreTokens()) {
          throw SpannerExceptionFactory.newSpannerException(
              ErrorCode.INVALID_ARGUMENT,
              "Invalid WHERE clause. Expression missing after AND: " + statement);
        }
      } else {
        break;
      }
    }
    return whereClausesBuilder.build();
  }

  static SpannerException createInvalidAssignmentException(Statement statement) {
    return SpannerExceptionFactory.newSpannerException(
        ErrorCode.INVALID_ARGUMENT,
        "Invalid assignment expression. "
            + "Only assignments in the form 'column_name1 = <literal | parameter>[, column_name2 = <literal | parameter> [...]]' are supported: "
            + statement);
  }

  static SpannerException createInvalidWhereClauseException(Statement statement) {
    return SpannerExceptionFactory.newSpannerException(
        ErrorCode.INVALID_ARGUMENT,
        "Invalid WHERE-clause for mutations. "
            + "Only WHERE-clause in the form 'column_name1 = <literal | parameter> [AND column_name2 = <literal | parameter> [...]]' are supported: "
            + statement);
  }

  static Value convertExpressionToValue(Statement statement, String valueExpression) {
    String parameterName = getQueryParameterName(valueExpression);
    if (parameterName != null) {
      if (statement.getParameters().containsKey(parameterName)) {
        return statement.getParameters().get(parameterName);
      } else {
        throw SpannerExceptionFactory.newSpannerException(
            ErrorCode.INVALID_ARGUMENT, "Unknown parameter name: " + parameterName);
      }
    } else {
      return parseValue(valueExpression);
    }
  }

  static String getQueryParameterName(String expression) {
    if (expression != null && expression.length() > 1 && expression.startsWith("@")) {
      SimpleParser parser = new SimpleParser(expression.substring(1));
      String name = parser.readIdentifierPart();
      if (name != null && !parser.hasMoreTokens()) {
        return name;
      }
    }
    return null;
  }

  static Value parseValue(String expression) {
    // TODO: Support more literals (Arrays).
    SimpleParser parser = new SimpleParser(expression);
    Value value;
    if (parser.eatKeyword("null")) {
      parser.throwIfHasMoreTokens();
      value =
          Value.untyped(
              com.google.protobuf.Value.newBuilder().setNullValue(NullValue.NULL_VALUE).build());
    } else if (parser.eatKeyword("true")) {
      parser.throwIfHasMoreTokens();
      value = Value.bool(true);
    } else if (parser.eatKeyword("false")) {
      parser.throwIfHasMoreTokens();
      value = Value.bool(false);
    } else if (parser.eatKeyword("numeric")) {
      QuotedString stringValue = parser.readSingleQuotedString();
      parser.throwIfHasMoreTokens();
      try {
        BigDecimal numericValue = new BigDecimal(stringValue.getValue());
        value = Value.numeric(numericValue);
      } catch (NumberFormatException e) {
        throw SpannerExceptionFactory.newSpannerException(
            ErrorCode.INVALID_ARGUMENT, "Invalid or unsupported numeric literal: " + expression, e);
      }
    } else if (parser.eatKeyword("date")) {
      QuotedString stringValue = parser.readSingleQuotedString();
      parser.throwIfHasMoreTokens();
      try {
        Date dateValue = Date.parseDate(stringValue.getValue());
        value = Value.date(dateValue);
      } catch (IllegalArgumentException e) {
        throw SpannerExceptionFactory.newSpannerException(
            ErrorCode.INVALID_ARGUMENT, "Invalid or unsupported date literal: " + expression, e);
      }
    } else if (parser.eatKeyword("timestamp")) {
      QuotedString stringValue = parser.readSingleQuotedString();
      parser.throwIfHasMoreTokens();
      try {
        Timestamp timestampValue = Timestamp.parseTimestamp(stringValue.getValue());
        value = Value.timestamp(timestampValue);
      } catch (IllegalArgumentException e) {
        throw SpannerExceptionFactory.newSpannerException(
            ErrorCode.INVALID_ARGUMENT,
            "Invalid or unsupported timestamp literal: " + expression,
            e);
      }
    } else if (parser.eatKeyword("json")) {
      QuotedString stringValue = parser.readSingleQuotedString();
      parser.throwIfHasMoreTokens();
      value = Value.json(stringValue.getValue());
    } else if (parser.peekToken("'")) {
      QuotedString stringValue = parser.readSingleQuotedString();
      parser.throwIfHasMoreTokens();
      value =
          Value.untyped(
              com.google.protobuf.Value.newBuilder()
                  .setStringValue(stringValue.getValue())
                  .build());
    } else if (parser.peekToken("\"")) {
      QuotedString stringValue = parser.readDoubleQuotedString();
      parser.throwIfHasMoreTokens();
      value =
          Value.untyped(
              com.google.protobuf.Value.newBuilder()
                  .setStringValue(stringValue.getValue())
                  .build());
    } else if (parser.getSql().equalsIgnoreCase("inf")
        || parser.getSql().equalsIgnoreCase("+inf")) {
      value = Value.float64(Double.POSITIVE_INFINITY);
    } else if (parser.getSql().equalsIgnoreCase("-inf")) {
      value = Value.float64(Double.NEGATIVE_INFINITY);
    } else if (parser.getSql().equalsIgnoreCase("nan")) {
      value = Value.float64(Double.NaN);
    } else {
      Double numberValue = null;
      try {
        numberValue = Double.valueOf(expression);
      } catch (NumberFormatException ignore) {
        // Ignore any errors and just send the value as an untyped string and let the backend
        // try to infer the value and type.
      }
      com.google.protobuf.Value.Builder valueBuilder =
          com.google.protobuf.Value.newBuilder().setStringValue(expression);
      if (numberValue != null) {
        valueBuilder.setNumberValue(numberValue);
      }
      value = Value.untyped(valueBuilder.build());
    }
    return value;
  }
}
