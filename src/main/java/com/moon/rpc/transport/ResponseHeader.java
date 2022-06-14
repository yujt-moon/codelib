package com.moon.rpc.transport;

import java.nio.charset.StandardCharsets;

/**
 * @author yujiangtao
 * @date 2021/1/30 下午2:31
 */
public class ResponseHeader extends Header {

    private int code;

    private String error;

    public ResponseHeader(int requestId, int version, int type, int code, String error) {
        super(requestId, version, type);
        this.code = code;
        this.error = error;
    }

    public ResponseHeader(int requestId, int version, int type) {
        this(requestId, version, type, Code.SUCCESS.getCode(), null);
    }

    public ResponseHeader(int requestId, int version, int type, Throwable throwable) {
        this(requestId, version, type, Code.UNKNOWN_ERROR.getCode(), throwable.getMessage());
    }

    @Override
    public int length() {
        return Integer.BYTES + Integer.BYTES + Integer.BYTES + Integer.BYTES +
                Integer.BYTES +
                (error == null ? 0 : error.getBytes(StandardCharsets.UTF_8).length);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
