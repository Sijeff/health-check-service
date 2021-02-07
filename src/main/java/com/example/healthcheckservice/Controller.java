package com.example.healthcheckservice;

import com.example.healthcheckservice.domain.CreateHealthCheckRequest;
import com.example.healthcheckservice.domain.HealthCheck;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class Controller {

    private final HealthCheckRepository healthCheckRepository;

    public Controller(HealthCheckRepository healthCheckRepository) {
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
    public ModelAndView getCreateHealthCheckForm(CreateHealthCheckRequest createHealthCheckRequest) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("form");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createHealthCheck(@Validated CreateHealthCheckRequest request, BindingResult result, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("form");
            return modelAndView;
        }
        try {
            URL url = new URL(request.getHealthCheckURL());
            healthCheckRepository.save(new HealthCheck(request.getServiceName(), url.toString()));
            modelAndView.setViewName("index");
            return modelAndView;
        } catch (MalformedURLException e) {
            modelAndView.setViewName("form");
            return modelAndView;
        }
    }

}
