package com.nam.chat.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("api")
@SecurityRequirement(name = "Keycloak")
public class HealthCheckController {

    @GetMapping("public/health")
    @PreAuthorize("hasRole('ROLE_HEALTH_CHECK')")
    public String health() {
        return "OK";
    }
}
