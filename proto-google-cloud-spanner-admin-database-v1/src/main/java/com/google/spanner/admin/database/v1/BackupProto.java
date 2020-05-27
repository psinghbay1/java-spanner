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
// source: google/spanner/admin/database/v1/backup.proto

package com.google.spanner.admin.database.v1;

public final class BackupProto {
  private BackupProto() {}

  public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {}

  public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
  }

  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_spanner_admin_database_v1_Backup_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_spanner_admin_database_v1_Backup_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_spanner_admin_database_v1_CreateBackupRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_spanner_admin_database_v1_CreateBackupRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_spanner_admin_database_v1_CreateBackupMetadata_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_spanner_admin_database_v1_CreateBackupMetadata_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_spanner_admin_database_v1_UpdateBackupRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_spanner_admin_database_v1_UpdateBackupRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_spanner_admin_database_v1_GetBackupRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_spanner_admin_database_v1_GetBackupRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_spanner_admin_database_v1_DeleteBackupRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_spanner_admin_database_v1_DeleteBackupRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_spanner_admin_database_v1_ListBackupsRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_spanner_admin_database_v1_ListBackupsRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_spanner_admin_database_v1_ListBackupsResponse_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_spanner_admin_database_v1_ListBackupsResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_spanner_admin_database_v1_ListBackupOperationsRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_spanner_admin_database_v1_ListBackupOperationsRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_spanner_admin_database_v1_ListBackupOperationsResponse_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_spanner_admin_database_v1_ListBackupOperationsResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_spanner_admin_database_v1_BackupInfo_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_spanner_admin_database_v1_BackupInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_spanner_admin_database_v1_CreateBackupEncryptionConfig_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_spanner_admin_database_v1_CreateBackupEncryptionConfig_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }

  private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

  static {
    java.lang.String[] descriptorData = {
      "\n-google/spanner/admin/database/v1/backu"
          + "p.proto\022 google.spanner.admin.database.v"
          + "1\032\037google/api/field_behavior.proto\032\031goog"
          + "le/api/resource.proto\032#google/longrunnin"
          + "g/operations.proto\032 google/protobuf/fiel"
          + "d_mask.proto\032\037google/protobuf/timestamp."
          + "proto\032-google/spanner/admin/database/v1/"
          + "common.proto\"\235\004\n\006Backup\0226\n\010database\030\002 \001("
          + "\tB$\372A!\n\037spanner.googleapis.com/Database\022"
          + "/\n\013expire_time\030\003 \001(\0132\032.google.protobuf.T"
          + "imestamp\022\014\n\004name\030\001 \001(\t\0224\n\013create_time\030\004 "
          + "\001(\0132\032.google.protobuf.TimestampB\003\340A\003\022\027\n\n"
          + "size_bytes\030\005 \001(\003B\003\340A\003\022B\n\005state\030\006 \001(\0162..g"
          + "oogle.spanner.admin.database.v1.Backup.S"
          + "tateB\003\340A\003\022\"\n\025referencing_databases\030\007 \003(\t"
          + "B\003\340A\003\022N\n\017encryption_info\030\010 \001(\01320.google."
          + "spanner.admin.database.v1.EncryptionInfo"
          + "B\003\340A\003\"7\n\005State\022\025\n\021STATE_UNSPECIFIED\020\000\022\014\n"
          + "\010CREATING\020\001\022\t\n\005READY\020\002:\\\352AY\n\035spanner.goo"
          + "gleapis.com/Backup\0228projects/{project}/i"
          + "nstances/{instance}/backups/{backup}\"\205\002\n"
          + "\023CreateBackupRequest\0227\n\006parent\030\001 \001(\tB\'\340A"
          + "\002\372A!\n\037spanner.googleapis.com/Instance\022\026\n"
          + "\tbackup_id\030\002 \001(\tB\003\340A\002\022=\n\006backup\030\003 \001(\0132(."
          + "google.spanner.admin.database.v1.BackupB"
          + "\003\340A\002\022^\n\021encryption_config\030\004 \001(\0132>.google"
          + ".spanner.admin.database.v1.CreateBackupE"
          + "ncryptionConfigB\003\340A\001\"\256\001\n\024CreateBackupMet"
          + "adata\022\014\n\004name\030\001 \001(\t\022\020\n\010database\030\002 \001(\t\022E\n"
          + "\010progress\030\003 \001(\01323.google.spanner.admin.d"
          + "atabase.v1.OperationProgress\022/\n\013cancel_t"
          + "ime\030\004 \001(\0132\032.google.protobuf.Timestamp\"\212\001"
          + "\n\023UpdateBackupRequest\022=\n\006backup\030\001 \001(\0132(."
          + "google.spanner.admin.database.v1.BackupB"
          + "\003\340A\002\0224\n\013update_mask\030\002 \001(\0132\032.google.proto"
          + "buf.FieldMaskB\003\340A\002\"G\n\020GetBackupRequest\0223"
          + "\n\004name\030\001 \001(\tB%\340A\002\372A\037\n\035spanner.googleapis"
          + ".com/Backup\"J\n\023DeleteBackupRequest\0223\n\004na"
          + "me\030\001 \001(\tB%\340A\002\372A\037\n\035spanner.googleapis.com"
          + "/Backup\"\204\001\n\022ListBackupsRequest\0227\n\006parent"
          + "\030\001 \001(\tB\'\340A\002\372A!\n\037spanner.googleapis.com/I"
          + "nstance\022\016\n\006filter\030\002 \001(\t\022\021\n\tpage_size\030\003 \001"
          + "(\005\022\022\n\npage_token\030\004 \001(\t\"i\n\023ListBackupsRes"
          + "ponse\0229\n\007backups\030\001 \003(\0132(.google.spanner."
          + "admin.database.v1.Backup\022\027\n\017next_page_to"
          + "ken\030\002 \001(\t\"\215\001\n\033ListBackupOperationsReques"
          + "t\0227\n\006parent\030\001 \001(\tB\'\340A\002\372A!\n\037spanner.googl"
          + "eapis.com/Instance\022\016\n\006filter\030\002 \001(\t\022\021\n\tpa"
          + "ge_size\030\003 \001(\005\022\022\n\npage_token\030\004 \001(\t\"j\n\034Lis"
          + "tBackupOperationsResponse\0221\n\noperations\030"
          + "\001 \003(\0132\035.google.longrunning.Operation\022\027\n\017"
          + "next_page_token\030\002 \001(\t\"f\n\nBackupInfo\022\016\n\006b"
          + "ackup\030\001 \001(\t\022/\n\013create_time\030\002 \001(\0132\032.googl"
          + "e.protobuf.Timestamp\022\027\n\017source_database\030"
          + "\003 \001(\t\"\335\002\n\034CreateBackupEncryptionConfig\022k"
          + "\n\017encryption_type\030\001 \001(\0162M.google.spanner"
          + ".admin.database.v1.CreateBackupEncryptio"
          + "nConfig.EncryptionTypeB\003\340A\002\022?\n\014kms_key_n"
          + "ame\030\002 \001(\tB)\340A\001\372A#\n!cloudkms.googleapis.c"
          + "om/CryptoKey\"\216\001\n\016EncryptionType\022\037\n\033ENCRY"
          + "PTION_TYPE_UNSPECIFIED\020\000\022\033\n\027USE_DATABASE"
          + "_ENCRYPTION\020\001\022\035\n\031GOOGLE_DEFAULT_ENCRYPTI"
          + "ON\020\002\022\037\n\033CUSTOMER_MANAGED_ENCRYPTION\020\003B\377\001"
          + "\n$com.google.spanner.admin.database.v1B\013"
          + "BackupProtoP\001ZHgoogle.golang.org/genprot"
          + "o/googleapis/spanner/admin/database/v1;d"
          + "atabase\252\002&Google.Cloud.Spanner.Admin.Dat"
          + "abase.V1\312\002&Google\\Cloud\\Spanner\\Admin\\Da"
          + "tabase\\V1\352\002+Google::Cloud::Spanner::Admi"
          + "n::Database::V1b\006proto3"
    };
    descriptor =
        com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
            descriptorData,
            new com.google.protobuf.Descriptors.FileDescriptor[] {
              com.google.api.FieldBehaviorProto.getDescriptor(),
              com.google.api.ResourceProto.getDescriptor(),
              com.google.longrunning.OperationsProto.getDescriptor(),
              com.google.protobuf.FieldMaskProto.getDescriptor(),
              com.google.protobuf.TimestampProto.getDescriptor(),
              com.google.spanner.admin.database.v1.CommonProto.getDescriptor(),
            });
    internal_static_google_spanner_admin_database_v1_Backup_descriptor =
        getDescriptor().getMessageTypes().get(0);
    internal_static_google_spanner_admin_database_v1_Backup_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_spanner_admin_database_v1_Backup_descriptor,
            new java.lang.String[] {
              "Database",
              "ExpireTime",
              "Name",
              "CreateTime",
              "SizeBytes",
              "State",
              "ReferencingDatabases",
              "EncryptionInfo",
            });
    internal_static_google_spanner_admin_database_v1_CreateBackupRequest_descriptor =
        getDescriptor().getMessageTypes().get(1);
    internal_static_google_spanner_admin_database_v1_CreateBackupRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_spanner_admin_database_v1_CreateBackupRequest_descriptor,
            new java.lang.String[] {
              "Parent", "BackupId", "Backup", "EncryptionConfig",
            });
    internal_static_google_spanner_admin_database_v1_CreateBackupMetadata_descriptor =
        getDescriptor().getMessageTypes().get(2);
    internal_static_google_spanner_admin_database_v1_CreateBackupMetadata_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_spanner_admin_database_v1_CreateBackupMetadata_descriptor,
            new java.lang.String[] {
              "Name", "Database", "Progress", "CancelTime",
            });
    internal_static_google_spanner_admin_database_v1_UpdateBackupRequest_descriptor =
        getDescriptor().getMessageTypes().get(3);
    internal_static_google_spanner_admin_database_v1_UpdateBackupRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_spanner_admin_database_v1_UpdateBackupRequest_descriptor,
            new java.lang.String[] {
              "Backup", "UpdateMask",
            });
    internal_static_google_spanner_admin_database_v1_GetBackupRequest_descriptor =
        getDescriptor().getMessageTypes().get(4);
    internal_static_google_spanner_admin_database_v1_GetBackupRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_spanner_admin_database_v1_GetBackupRequest_descriptor,
            new java.lang.String[] {
              "Name",
            });
    internal_static_google_spanner_admin_database_v1_DeleteBackupRequest_descriptor =
        getDescriptor().getMessageTypes().get(5);
    internal_static_google_spanner_admin_database_v1_DeleteBackupRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_spanner_admin_database_v1_DeleteBackupRequest_descriptor,
            new java.lang.String[] {
              "Name",
            });
    internal_static_google_spanner_admin_database_v1_ListBackupsRequest_descriptor =
        getDescriptor().getMessageTypes().get(6);
    internal_static_google_spanner_admin_database_v1_ListBackupsRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_spanner_admin_database_v1_ListBackupsRequest_descriptor,
            new java.lang.String[] {
              "Parent", "Filter", "PageSize", "PageToken",
            });
    internal_static_google_spanner_admin_database_v1_ListBackupsResponse_descriptor =
        getDescriptor().getMessageTypes().get(7);
    internal_static_google_spanner_admin_database_v1_ListBackupsResponse_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_spanner_admin_database_v1_ListBackupsResponse_descriptor,
            new java.lang.String[] {
              "Backups", "NextPageToken",
            });
    internal_static_google_spanner_admin_database_v1_ListBackupOperationsRequest_descriptor =
        getDescriptor().getMessageTypes().get(8);
    internal_static_google_spanner_admin_database_v1_ListBackupOperationsRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_spanner_admin_database_v1_ListBackupOperationsRequest_descriptor,
            new java.lang.String[] {
              "Parent", "Filter", "PageSize", "PageToken",
            });
    internal_static_google_spanner_admin_database_v1_ListBackupOperationsResponse_descriptor =
        getDescriptor().getMessageTypes().get(9);
    internal_static_google_spanner_admin_database_v1_ListBackupOperationsResponse_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_spanner_admin_database_v1_ListBackupOperationsResponse_descriptor,
            new java.lang.String[] {
              "Operations", "NextPageToken",
            });
    internal_static_google_spanner_admin_database_v1_BackupInfo_descriptor =
        getDescriptor().getMessageTypes().get(10);
    internal_static_google_spanner_admin_database_v1_BackupInfo_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_spanner_admin_database_v1_BackupInfo_descriptor,
            new java.lang.String[] {
              "Backup", "CreateTime", "SourceDatabase",
            });
    internal_static_google_spanner_admin_database_v1_CreateBackupEncryptionConfig_descriptor =
        getDescriptor().getMessageTypes().get(11);
    internal_static_google_spanner_admin_database_v1_CreateBackupEncryptionConfig_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_spanner_admin_database_v1_CreateBackupEncryptionConfig_descriptor,
            new java.lang.String[] {
              "EncryptionType", "KmsKeyName",
            });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.google.api.FieldBehaviorProto.fieldBehavior);
    registry.add(com.google.api.ResourceProto.resource);
    registry.add(com.google.api.ResourceProto.resourceReference);
    com.google.protobuf.Descriptors.FileDescriptor.internalUpdateFileDescriptor(
        descriptor, registry);
    com.google.api.FieldBehaviorProto.getDescriptor();
    com.google.api.ResourceProto.getDescriptor();
    com.google.longrunning.OperationsProto.getDescriptor();
    com.google.protobuf.FieldMaskProto.getDescriptor();
    com.google.protobuf.TimestampProto.getDescriptor();
    com.google.spanner.admin.database.v1.CommonProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
