/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/spanner/admin/instance/v1/spanner_instance_admin.proto

package com.google.spanner.admin.instance.v1;

public interface UpdateInstanceMetadataOrBuilder
    extends
    // @@protoc_insertion_point(interface_extends:google.spanner.admin.instance.v1.UpdateInstanceMetadata)
    com.google.protobuf.MessageOrBuilder {

  /**
   *
   *
   * <pre>
   * The desired end state of the update.
   * </pre>
   *
   * <code>.google.spanner.admin.instance.v1.Instance instance = 1;</code>
   *
   * @return Whether the instance field is set.
   */
  boolean hasInstance();
  /**
   *
   *
   * <pre>
   * The desired end state of the update.
   * </pre>
   *
   * <code>.google.spanner.admin.instance.v1.Instance instance = 1;</code>
   *
   * @return The instance.
   */
  com.google.spanner.admin.instance.v1.Instance getInstance();
  /**
   *
   *
   * <pre>
   * The desired end state of the update.
   * </pre>
   *
   * <code>.google.spanner.admin.instance.v1.Instance instance = 1;</code>
   */
  com.google.spanner.admin.instance.v1.InstanceOrBuilder getInstanceOrBuilder();

  /**
   *
   *
   * <pre>
   * The time at which [UpdateInstance][google.spanner.admin.instance.v1.InstanceAdmin.UpdateInstance]
   * request was received.
   * </pre>
   *
   * <code>.google.protobuf.Timestamp start_time = 2;</code>
   *
   * @return Whether the startTime field is set.
   */
  boolean hasStartTime();
  /**
   *
   *
   * <pre>
   * The time at which [UpdateInstance][google.spanner.admin.instance.v1.InstanceAdmin.UpdateInstance]
   * request was received.
   * </pre>
   *
   * <code>.google.protobuf.Timestamp start_time = 2;</code>
   *
   * @return The startTime.
   */
  com.google.protobuf.Timestamp getStartTime();
  /**
   *
   *
   * <pre>
   * The time at which [UpdateInstance][google.spanner.admin.instance.v1.InstanceAdmin.UpdateInstance]
   * request was received.
   * </pre>
   *
   * <code>.google.protobuf.Timestamp start_time = 2;</code>
   */
  com.google.protobuf.TimestampOrBuilder getStartTimeOrBuilder();

  /**
   *
   *
   * <pre>
   * The time at which this operation was cancelled. If set, this operation is
   * in the process of undoing itself (which is guaranteed to succeed) and
   * cannot be cancelled again.
   * </pre>
   *
   * <code>.google.protobuf.Timestamp cancel_time = 3;</code>
   *
   * @return Whether the cancelTime field is set.
   */
  boolean hasCancelTime();
  /**
   *
   *
   * <pre>
   * The time at which this operation was cancelled. If set, this operation is
   * in the process of undoing itself (which is guaranteed to succeed) and
   * cannot be cancelled again.
   * </pre>
   *
   * <code>.google.protobuf.Timestamp cancel_time = 3;</code>
   *
   * @return The cancelTime.
   */
  com.google.protobuf.Timestamp getCancelTime();
  /**
   *
   *
   * <pre>
   * The time at which this operation was cancelled. If set, this operation is
   * in the process of undoing itself (which is guaranteed to succeed) and
   * cannot be cancelled again.
   * </pre>
   *
   * <code>.google.protobuf.Timestamp cancel_time = 3;</code>
   */
  com.google.protobuf.TimestampOrBuilder getCancelTimeOrBuilder();

  /**
   *
   *
   * <pre>
   * The time at which this operation failed or was completed successfully.
   * </pre>
   *
   * <code>.google.protobuf.Timestamp end_time = 4;</code>
   *
   * @return Whether the endTime field is set.
   */
  boolean hasEndTime();
  /**
   *
   *
   * <pre>
   * The time at which this operation failed or was completed successfully.
   * </pre>
   *
   * <code>.google.protobuf.Timestamp end_time = 4;</code>
   *
   * @return The endTime.
   */
  com.google.protobuf.Timestamp getEndTime();
  /**
   *
   *
   * <pre>
   * The time at which this operation failed or was completed successfully.
   * </pre>
   *
   * <code>.google.protobuf.Timestamp end_time = 4;</code>
   */
  com.google.protobuf.TimestampOrBuilder getEndTimeOrBuilder();
}
