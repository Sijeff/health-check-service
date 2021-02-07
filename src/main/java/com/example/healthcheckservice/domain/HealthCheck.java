package com.example.healthcheckservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class HealthCheck {

    @Id
    @GeneratedValue
    private Long id;
    private String serviceName;
    private String healthCheckUrl;
    private boolean isHealthy;
    private Instant createdAt;

    public HealthCheck() {
    }

    public HealthCheck(String serviceName, String healthCheckUrl) {
        this.serviceName = serviceName;
        this.healthCheckUrl = healthCheckUrl;
        this.createdAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getHealthCheckUrl() {
        return healthCheckUrl;
    }

    public boolean getIsHealthy() {
        return isHealthy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setHealthCheckUrl(String healthCheckURL) {
        this.healthCheckUrl = healthCheckURL;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "HealthCheck{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", healthCheckURL='" + healthCheckUrl + '\'' +
                ", isHealthy=" + isHealthy +
                ", createdAt=" + createdAt +
                '}';
    }
}
