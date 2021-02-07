package com.example.healthcheckservice;

import com.example.healthcheckservice.domain.CreateHealthCheckRequest;
import com.example.healthcheckservice.domain.HealthCheck;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.MalformedURLException;
import java.net.URL;

@Controller
public class HealthCheckController {

    private final HealthCheckRepository healthCheckRepository;

    public HealthCheckController(HealthCheckRepository healthCheckRepository) {
        this.healthCheckRepository = healthCheckRepository;
    }

    @GetMapping(value = {"/", "/index"})
    public ModelAndView listHealthChecks() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("healthChecks", healthCheckRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/form")
    public String getCreateHealthCheckForm(CreateHealthCheckRequest createHealthCheckRequest) {
        return "form";
    }

    @PostMapping("/create")
    public String createHealthCheck(@Validated CreateHealthCheckRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        try {
            URL url = new URL(request.getHealthCheckURL());
            healthCheckRepository.save(new HealthCheck(request.getServiceName(), url.toString()));
            return "redirect:/index";
        } catch (MalformedURLException e) {
            return "form";
        }
    }

}
