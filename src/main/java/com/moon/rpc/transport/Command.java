package com.moon.rpc.transport;

/**
 * @author yujiangtao
 * @date 2021/1/30 下午2:29
 */
public class Command {

    protected Header header;

    private byte[] payload;

    public Command(Header header, byte[] payload) {
        this.header = header;
        this.payload = payload;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }
}
