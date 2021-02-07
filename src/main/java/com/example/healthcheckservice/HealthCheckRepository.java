package com.example.healthcheckservice;

import com.example.healthcheckservice.domain.HealthCheck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthCheckRepository extends CrudRepository<HealthCheck, String> {


}
