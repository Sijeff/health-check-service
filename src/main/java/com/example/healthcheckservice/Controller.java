package com.example.healthcheckservice;

import com.example.healthcheckservice.domain.CreateHealthCheckRequest;
import com.example.healthcheckservice.domain.HealthCheck;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class Controller {

    private final HealthCheckRepository healthCheckRepository;

    public Controller(HealthCheckRepository healthCheckRepository) {
        this.healthCheckRepository = healthCheckRepository;
    }

    @GetMapping("/")
    public ModelAndView listHealthChecks() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("healthChecks", healthCheckRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView getCreateHealthCheckForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("form");
        return modelAndView;
    }

    @PostMapping("/create")
    public String createHealthCheck(@Validated CreateHealthCheckRequest request) {
        System.out.println(request);
        try {
            URI uri = new URI(request.getHealthCheckURL());
            healthCheckRepository.save(new HealthCheck(request.getServiceName(), uri.toString()));
            return "redirect:/index";
        } catch (URISyntaxException e) {
            return "form";
        }
    }

}
