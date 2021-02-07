package com.example.healthcheckservice.client;

import com.example.healthcheckservice.HealthCheckRepository;
import com.example.healthcheckservice.domain.HealthCheck;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class HealthCheckService {

    private final HttpClient client;
    private final HealthCheckRepository repository;

    public HealthCheckService(HttpClient client, HealthCheckRepository repository) {
        this.client = client;
        this.repository = repository;
    }

    @Scheduled(fixedDelayString = "${fixedDelayMillis:2000}")
    public void pollAllHealthChecks() {
        repository.findAll().forEach(this::pollService);
    }

    private void pollService(HealthCheck healthCheck) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(healthCheck.getHealthCheckUrl()))
                    .GET()
                    .build();
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            if (response.statusCode() == 200 && !healthCheck.getIsHealthy()) {
                healthCheck.setHealthy(true);
                repository.save(healthCheck);
            } else if (response.statusCode() != 200 && healthCheck.getIsHealthy()) {
                healthCheck.setHealthy(false);
                repository.save(healthCheck);
            }

        } catch (URISyntaxException | IOException | InterruptedException e) {
            healthCheck.setHealthy(false);
            repository.save(healthCheck);
        }


    }


}
