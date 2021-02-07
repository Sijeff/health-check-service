package com.example.healthcheckservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;
import java.time.Duration;

@Configuration
public class HealthCheckConfig {

    @Value("${timeoutInMillis:500}")
    private int timeoutInMillis;

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(timeoutInMillis))
                .build();
    }
}
