// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: result.proto

package org.derecalliance.derec.protobuf;

public final class ResultOuterClass {
  private ResultOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * <pre>
   *
   * The success or failure of processing a request,
   * used in DeRec Response messages
   * </pre>
   *
   * Protobuf enum {@code org.derecalliance.derec.protobuf.StatusEnum}
   */
  public enum StatusEnum
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <pre>
     * The request was successfully handled. 
     * </pre>
     *
     * <code>OK = 0;</code>
     */
    OK(0),
    /**
     * <pre>
     *
     * The request was partially fulfilled. The memo will give more details.
     * </pre>
     *
     * <code>PARTIAL = 1;</code>
     */
    PARTIAL(1),
    /**
     * <pre>
     *
     * The request fails for some reason other than one of the specific
     * reasons below.
     * </pre>
     *
     * <code>FAIL = 2;</code>
     */
    FAIL(2),
    /**
     * <pre>
     *
     * This request fails because it would cause the helper to be storing
     * more bytes for this sharer than the agreed limit for this secret ID.
     * </pre>
     *
     * <code>SIZE_LIMIT_EXCEEDED = 3;</code>
     */
    SIZE_LIMIT_EXCEEDED(3),
    /**
     * <pre>
     * the request is being ignored because it is too frequent (it was
     * sent too soon after the last request of that type, according to
     * the agreed limit on the frequency.
     * </pre>
     *
     * <code>TOO_FREQUENT = 4;</code>
     */
    TOO_FREQUENT(4),
    /**
     * <pre>
     * This secret ID is not stored by this helper. 
     * </pre>
     *
     * <code>UNKNOWN_SECRET_ID = 5;</code>
     */
    UNKNOWN_SECRET_ID(5),
    /**
     * <pre>
     * This share version for this secret ID not stored by this helper. 
     * </pre>
     *
     * <code>UNKNOWN_SHARE_VERSION = 6;</code>
     */
    UNKNOWN_SHARE_VERSION(6),
    /**
     * <pre>
     * The received message could not be decrypted successfully. 
     * </pre>
     *
     * <code>DECRYPTION_FAILED = 7;</code>
     */
    DECRYPTION_FAILED(7),
    /**
     * <pre>
     * Signature could not be verified 
     * </pre>
     *
     * <code>VERIFICATION_FAILED = 8;</code>
     */
    VERIFICATION_FAILED(8),
    /**
     * <pre>
     * Format error - includes errors like protobuf parsing failure or invalid message formatting. 
     * </pre>
     *
     * <code>FORMAT_ERROR = 9;</code>
     */
    FORMAT_ERROR(9),
    /**
     * <pre>
     * the helper is asking the sharer to send an unpair request 
     * </pre>
     *
     * <code>REQUEST_TO_CLOSE = 99;</code>
     */
    REQUEST_TO_CLOSE(99),
    UNRECOGNIZED(-1),
    ;

    /**
     * <pre>
     * The request was successfully handled. 
     * </pre>
     *
     * <code>OK = 0;</code>
     */
    public static final int OK_VALUE = 0;
    /**
     * <pre>
     *
     * The request was partially fulfilled. The memo will give more details.
     * </pre>
     *
     * <code>PARTIAL = 1;</code>
     */
    public static final int PARTIAL_VALUE = 1;
    /**
     * <pre>
     *
     * The request fails for some reason other than one of the specific
     * reasons below.
     * </pre>
     *
     * <code>FAIL = 2;</code>
     */
    public static final int FAIL_VALUE = 2;
    /**
     * <pre>
     *
     * This request fails because it would cause the helper to be storing
     * more bytes for this sharer than the agreed limit for this secret ID.
     * </pre>
     *
     * <code>SIZE_LIMIT_EXCEEDED = 3;</code>
     */
    public static final int SIZE_LIMIT_EXCEEDED_VALUE = 3;
    /**
     * <pre>
     * the request is being ignored because it is too frequent (it was
     * sent too soon after the last request of that type, according to
     * the agreed limit on the frequency.
     * </pre>
     *
     * <code>TOO_FREQUENT = 4;</code>
     */
    public static final int TOO_FREQUENT_VALUE = 4;
    /**
     * <pre>
     * This secret ID is not stored by this helper. 
     * </pre>
     *
     * <code>UNKNOWN_SECRET_ID = 5;</code>
     */
    public static final int UNKNOWN_SECRET_ID_VALUE = 5;
    /**
     * <pre>
     * This share version for this secret ID not stored by this helper. 
     * </pre>
     *
     * <code>UNKNOWN_SHARE_VERSION = 6;</code>
     */
    public static final int UNKNOWN_SHARE_VERSION_VALUE = 6;
    /**
     * <pre>
     * The received message could not be decrypted successfully. 
     * </pre>
     *
     * <code>DECRYPTION_FAILED = 7;</code>
     */
    public static final int DECRYPTION_FAILED_VALUE = 7;
    /**
     * <pre>
     * Signature could not be verified 
     * </pre>
     *
     * <code>VERIFICATION_FAILED = 8;</code>
     */
    public static final int VERIFICATION_FAILED_VALUE = 8;
    /**
     * <pre>
     * Format error - includes errors like protobuf parsing failure or invalid message formatting. 
     * </pre>
     *
     * <code>FORMAT_ERROR = 9;</code>
     */
    public static final int FORMAT_ERROR_VALUE = 9;
    /**
     * <pre>
     * the helper is asking the sharer to send an unpair request 
     * </pre>
     *
     * <code>REQUEST_TO_CLOSE = 99;</code>
     */
    public static final int REQUEST_TO_CLOSE_VALUE = 99;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static StatusEnum valueOf(int value) {
      return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static StatusEnum forNumber(int value) {
      switch (value) {
        case 0: return OK;
        case 1: return PARTIAL;
        case 2: return FAIL;
        case 3: return SIZE_LIMIT_EXCEEDED;
        case 4: return TOO_FREQUENT;
        case 5: return UNKNOWN_SECRET_ID;
        case 6: return UNKNOWN_SHARE_VERSION;
        case 7: return DECRYPTION_FAILED;
        case 8: return VERIFICATION_FAILED;
        case 9: return FORMAT_ERROR;
        case 99: return REQUEST_TO_CLOSE;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<StatusEnum>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        StatusEnum> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<StatusEnum>() {
            public StatusEnum findValueByNumber(int number) {
              return StatusEnum.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalStateException(
            "Can't get the descriptor of an unrecognized enum value.");
      }
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return org.derecalliance.derec.protobuf.ResultOuterClass.getDescriptor().getEnumTypes().get(0);
    }

    private static final StatusEnum[] VALUES = values();

    public static StatusEnum valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private StatusEnum(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:org.derecalliance.derec.protobuf.StatusEnum)
  }

  public interface ResultOrBuilder extends
      // @@protoc_insertion_point(interface_extends:org.derecalliance.derec.protobuf.Result)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.org.derecalliance.derec.protobuf.StatusEnum status = 1;</code>
     * @return The enum numeric value on the wire for status.
     */
    int getStatusValue();
    /**
     * <code>.org.derecalliance.derec.protobuf.StatusEnum status = 1;</code>
     * @return The status.
     */
    org.derecalliance.derec.protobuf.ResultOuterClass.StatusEnum getStatus();

    /**
     * <code>string memo = 2;</code>
     * @return The memo.
     */
    java.lang.String getMemo();
    /**
     * <code>string memo = 2;</code>
     * @return The bytes for memo.
     */
    com.google.protobuf.ByteString
        getMemoBytes();
  }
  /**
   * <pre>
   *
   * Result of success or failure for processing the request messages
   * </pre>
   *
   * Protobuf type {@code org.derecalliance.derec.protobuf.Result}
   */
  public static final class Result extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:org.derecalliance.derec.protobuf.Result)
      ResultOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Result.newBuilder() to construct.
    private Result(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Result() {
      status_ = 0;
      memo_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Result();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.derecalliance.derec.protobuf.ResultOuterClass.internal_static_org_derecalliance_derec_protobuf_Result_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.derecalliance.derec.protobuf.ResultOuterClass.internal_static_org_derecalliance_derec_protobuf_Result_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.derecalliance.derec.protobuf.ResultOuterClass.Result.class, org.derecalliance.derec.protobuf.ResultOuterClass.Result.Builder.class);
    }

    public static final int STATUS_FIELD_NUMBER = 1;
    private int status_ = 0;
    /**
     * <code>.org.derecalliance.derec.protobuf.StatusEnum status = 1;</code>
     * @return The enum numeric value on the wire for status.
     */
    @java.lang.Override public int getStatusValue() {
      return status_;
    }
    /**
     * <code>.org.derecalliance.derec.protobuf.StatusEnum status = 1;</code>
     * @return The status.
     */
    @java.lang.Override public org.derecalliance.derec.protobuf.ResultOuterClass.StatusEnum getStatus() {
      org.derecalliance.derec.protobuf.ResultOuterClass.StatusEnum result = org.derecalliance.derec.protobuf.ResultOuterClass.StatusEnum.forNumber(status_);
      return result == null ? org.derecalliance.derec.protobuf.ResultOuterClass.StatusEnum.UNRECOGNIZED : result;
    }

    public static final int MEMO_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object memo_ = "";
    /**
     * <code>string memo = 2;</code>
     * @return The memo.
     */
    @java.lang.Override
    public java.lang.String getMemo() {
      java.lang.Object ref = memo_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        memo_ = s;
        return s;
      }
    }
    /**
     * <code>string memo = 2;</code>
     * @return The bytes for memo.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getMemoBytes() {
      java.lang.Object ref = memo_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        memo_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (status_ != org.derecalliance.derec.protobuf.ResultOuterClass.StatusEnum.OK.getNumber()) {
        output.writeEnum(1, status_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(memo_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, memo_);
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (status_ != org.derecalliance.derec.protobuf.ResultOuterClass.StatusEnum.OK.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(1, status_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(memo_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, memo_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof org.derecalliance.derec.protobuf.ResultOuterClass.Result)) {
        return super.equals(obj);
      }
      org.derecalliance.derec.protobuf.ResultOuterClass.Result other = (org.derecalliance.derec.protobuf.ResultOuterClass.Result) obj;

      if (status_ != other.status_) return false;
      if (!getMemo()
          .equals(other.getMemo())) return false;
      if (!getUnknownFields().equals(other.getUnknownFields())) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + STATUS_FIELD_NUMBER;
      hash = (53 * hash) + status_;
      hash = (37 * hash) + MEMO_FIELD_NUMBER;
      hash = (53 * hash) + getMemo().hashCode();
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static org.derecalliance.derec.protobuf.ResultOuterClass.Result parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.derecalliance.derec.protobuf.ResultOuterClass.Result parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.derecalliance.derec.protobuf.ResultOuterClass.Result parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.derecalliance.derec.protobuf.ResultOuterClass.Result parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.derecalliance.derec.protobuf.ResultOuterClass.Result parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.derecalliance.derec.protobuf.ResultOuterClass.Result parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.derecalliance.derec.protobuf.ResultOuterClass.Result parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.derecalliance.derec.protobuf.ResultOuterClass.Result parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static org.derecalliance.derec.protobuf.ResultOuterClass.Result parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static org.derecalliance.derec.protobuf.ResultOuterClass.Result parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static org.derecalliance.derec.protobuf.ResultOuterClass.Result parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.derecalliance.derec.protobuf.ResultOuterClass.Result parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(org.derecalliance.derec.protobuf.ResultOuterClass.Result prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     *
     * Result of success or failure for processing the request messages
     * </pre>
     *
     * Protobuf type {@code org.derecalliance.derec.protobuf.Result}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:org.derecalliance.derec.protobuf.Result)
        org.derecalliance.derec.protobuf.ResultOuterClass.ResultOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return org.derecalliance.derec.protobuf.ResultOuterClass.internal_static_org_derecalliance_derec_protobuf_Result_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return org.derecalliance.derec.protobuf.ResultOuterClass.internal_static_org_derecalliance_derec_protobuf_Result_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                org.derecalliance.derec.protobuf.ResultOuterClass.Result.class, org.derecalliance.derec.protobuf.ResultOuterClass.Result.Builder.class);
      }

      // Construct using org.derecalliance.derec.protobuf.ResultOuterClass.Result.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        bitField0_ = 0;
        status_ = 0;
        memo_ = "";
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return org.derecalliance.derec.protobuf.ResultOuterClass.internal_static_org_derecalliance_derec_protobuf_Result_descriptor;
      }

      @java.lang.Override
      public org.derecalliance.derec.protobuf.ResultOuterClass.Result getDefaultInstanceForType() {
        return org.derecalliance.derec.protobuf.ResultOuterClass.Result.getDefaultInstance();
      }

      @java.lang.Override
      public org.derecalliance.derec.protobuf.ResultOuterClass.Result build() {
        org.derecalliance.derec.protobuf.ResultOuterClass.Result result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public org.derecalliance.derec.protobuf.ResultOuterClass.Result buildPartial() {
        org.derecalliance.derec.protobuf.ResultOuterClass.Result result = new org.derecalliance.derec.protobuf.ResultOuterClass.Result(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(org.derecalliance.derec.protobuf.ResultOuterClass.Result result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.status_ = status_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.memo_ = memo_;
        }
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof org.derecalliance.derec.protobuf.ResultOuterClass.Result) {
          return mergeFrom((org.derecalliance.derec.protobuf.ResultOuterClass.Result)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(org.derecalliance.derec.protobuf.ResultOuterClass.Result other) {
        if (other == org.derecalliance.derec.protobuf.ResultOuterClass.Result.getDefaultInstance()) return this;
        if (other.status_ != 0) {
          setStatusValue(other.getStatusValue());
        }
        if (!other.getMemo().isEmpty()) {
          memo_ = other.memo_;
          bitField0_ |= 0x00000002;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              case 8: {
                status_ = input.readEnum();
                bitField0_ |= 0x00000001;
                break;
              } // case 8
              case 18: {
                memo_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000002;
                break;
              } // case 18
              default: {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
            } // switch (tag)
          } // while (!done)
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } // finally
        return this;
      }
      private int bitField0_;

      private int status_ = 0;
      /**
       * <code>.org.derecalliance.derec.protobuf.StatusEnum status = 1;</code>
       * @return The enum numeric value on the wire for status.
       */
      @java.lang.Override public int getStatusValue() {
        return status_;
      }
      /**
       * <code>.org.derecalliance.derec.protobuf.StatusEnum status = 1;</code>
       * @param value The enum numeric value on the wire for status to set.
       * @return This builder for chaining.
       */
      public Builder setStatusValue(int value) {
        status_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <code>.org.derecalliance.derec.protobuf.StatusEnum status = 1;</code>
       * @return The status.
       */
      @java.lang.Override
      public org.derecalliance.derec.protobuf.ResultOuterClass.StatusEnum getStatus() {
        org.derecalliance.derec.protobuf.ResultOuterClass.StatusEnum result = org.derecalliance.derec.protobuf.ResultOuterClass.StatusEnum.forNumber(status_);
        return result == null ? org.derecalliance.derec.protobuf.ResultOuterClass.StatusEnum.UNRECOGNIZED : result;
      }
      /**
       * <code>.org.derecalliance.derec.protobuf.StatusEnum status = 1;</code>
       * @param value The status to set.
       * @return This builder for chaining.
       */
      public Builder setStatus(org.derecalliance.derec.protobuf.ResultOuterClass.StatusEnum value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000001;
        status_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <code>.org.derecalliance.derec.protobuf.StatusEnum status = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearStatus() {
        bitField0_ = (bitField0_ & ~0x00000001);
        status_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object memo_ = "";
      /**
       * <code>string memo = 2;</code>
       * @return The memo.
       */
      public java.lang.String getMemo() {
        java.lang.Object ref = memo_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          memo_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string memo = 2;</code>
       * @return The bytes for memo.
       */
      public com.google.protobuf.ByteString
          getMemoBytes() {
        java.lang.Object ref = memo_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          memo_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string memo = 2;</code>
       * @param value The memo to set.
       * @return This builder for chaining.
       */
      public Builder setMemo(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        memo_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <code>string memo = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearMemo() {
        memo_ = getDefaultInstance().getMemo();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
        return this;
      }
      /**
       * <code>string memo = 2;</code>
       * @param value The bytes for memo to set.
       * @return This builder for chaining.
       */
      public Builder setMemoBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        memo_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:org.derecalliance.derec.protobuf.Result)
    }

    // @@protoc_insertion_point(class_scope:org.derecalliance.derec.protobuf.Result)
    private static final org.derecalliance.derec.protobuf.ResultOuterClass.Result DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new org.derecalliance.derec.protobuf.ResultOuterClass.Result();
    }

    public static org.derecalliance.derec.protobuf.ResultOuterClass.Result getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Result>
        PARSER = new com.google.protobuf.AbstractParser<Result>() {
      @java.lang.Override
      public Result parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (com.google.protobuf.UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(e)
              .setUnfinishedMessage(builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<Result> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Result> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public org.derecalliance.derec.protobuf.ResultOuterClass.Result getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_derecalliance_derec_protobuf_Result_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_org_derecalliance_derec_protobuf_Result_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014result.proto\022 org.derecalliance.derec." +
      "protobuf\"T\n\006Result\022<\n\006status\030\001 \001(\0162,.org" +
      ".derecalliance.derec.protobuf.StatusEnum" +
      "\022\014\n\004memo\030\002 \001(\t*\340\001\n\nStatusEnum\022\006\n\002OK\020\000\022\013\n" +
      "\007PARTIAL\020\001\022\010\n\004FAIL\020\002\022\027\n\023SIZE_LIMIT_EXCEE" +
      "DED\020\003\022\020\n\014TOO_FREQUENT\020\004\022\025\n\021UNKNOWN_SECRE" +
      "T_ID\020\005\022\031\n\025UNKNOWN_SHARE_VERSION\020\006\022\025\n\021DEC" +
      "RYPTION_FAILED\020\007\022\027\n\023VERIFICATION_FAILED\020" +
      "\010\022\020\n\014FORMAT_ERROR\020\t\022\024\n\020REQUEST_TO_CLOSE\020" +
      "cb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_org_derecalliance_derec_protobuf_Result_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_org_derecalliance_derec_protobuf_Result_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_org_derecalliance_derec_protobuf_Result_descriptor,
        new java.lang.String[] { "Status", "Memo", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
