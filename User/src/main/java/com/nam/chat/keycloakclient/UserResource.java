package com.nam.chat.keycloakclient;

import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nam.chat.dto.Role;
import com.nam.chat.dto.User;
import com.nam.chat.security.KeycloakSecurityUtil;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/keycloak")
@SecurityRequirement(name = "Keycloak")
@RequiredArgsConstructor
public class UserResource {

    private final KeycloakSecurityUtil keycloakSecurityUtil;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Value("${realm}")
    private String realm;

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        List<UserRepresentation> userRepresentations = keycloak.realm(realm).users().list();
        return ResponseEntity.ok(userMapper.mapUsers(userRepresentations));
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        UserRepresentation userRepresentations = keycloak.realm(realm).users().get(id).toRepresentation();
        return ResponseEntity.ok(userMapper.mapUser(userRepresentations));
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(User user) {
        UserRepresentation userRepresentation = userMapper.mapUserRep(user);
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        keycloak.realm(realm).users().create(userRepresentation);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(User user) {
        UserRepresentation userRepresentation = userMapper.mapUserRep(user);
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        keycloak.realm(realm).users().get(user.getId()).update(userRepresentation);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/{id}/roles")
    public ResponseEntity<List<Role>> getRoles(@PathVariable("id") String id) {
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        return ResponseEntity.ok(roleMapper
                .mapRoles(keycloak.realm(realm).users().get(id).roles().realmLevel().listAll()));
    }
}
