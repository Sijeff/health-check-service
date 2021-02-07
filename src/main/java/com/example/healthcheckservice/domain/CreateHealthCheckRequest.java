package com.example.healthcheckservice.domain;

public class CreateHealthCheckRequest {

    private final String serviceName;
    private final String healthCheckURL;

    public CreateHealthCheckRequest(String serviceName, String healthCheckURL) {
        this.serviceName = serviceName;
        this.healthCheckURL = healthCheckURL;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getHealthCheckURL() {
        return healthCheckURL;
    }

    @Override
    public String toString() {
        return "CreateHealthCheckRequest{" +
                "serviceName='" + serviceName + '\'' +
                ", healthCheckURL='" + healthCheckURL + '\'' +
                '}';
    }
}
