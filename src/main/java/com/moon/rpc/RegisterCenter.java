package com.moon.rpc;

/**
 * @author yujiangtao
 * @date 2021/2/21 下午10:25
 */
public class RegisterCenter {

    private int id;

    private String serviceName;

    private String serviceUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    @Override
    public String toString() {
        return "RegisterCenter{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", serviceUrl='" + serviceUrl + '\'' +
                '}';
    }
}
