package com.nam.chat.keycloakclient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Service;
import com.nam.chat.dto.Role;

@Service
public class RoleMapper {
    public Role mapRole(RoleRepresentation roleRepresentation) {
        return Role.builder().id(roleRepresentation.getId()).name(roleRepresentation.getName())
                .build();
    }

    public List<Role> mapRoles(List<RoleRepresentation> roleRepresentations) {
        return Optional.ofNullable(roleRepresentations).orElse(Collections.emptyList()).stream()
                .map(this::mapRole).collect(Collectors.toList());
    }
}
