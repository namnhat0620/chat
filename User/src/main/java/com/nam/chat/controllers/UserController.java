package com.nam.chat.controllers;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nam.chat.dto.User;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("api")
@SecurityRequirement(name = "Keycloak")
public class UserController {

    @GetMapping("users/findByUsername/{username}")
    @PreAuthorize("hasRole('ROLE_HEALTH_CHECK')")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(User.builder().build());
    }
}
