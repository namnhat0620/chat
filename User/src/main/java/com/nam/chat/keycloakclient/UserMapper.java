package com.nam.chat.keycloakclient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import com.nam.chat.dto.User;

@Service
public class UserMapper {
    public User mapUser(UserRepresentation userRepresentation) {
        return User.builder().id(userRepresentation.getId())
                .firstName(userRepresentation.getFirstName())
                .lastName(userRepresentation.getLastName()).email(userRepresentation.getEmail())
                .username(userRepresentation.getUsername()).build();
    }

    public UserRepresentation mapUserRep(User user) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(user.getFirstName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
        List<CredentialRepresentation> listCredentials = new ArrayList<>();
        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setTemporary(false);
        cred.setValue(user.getPassword());
        listCredentials.add(cred);
        userRepresentation.setCredentials(listCredentials);
        return userRepresentation;
    }

    public List<User> mapUsers(List<UserRepresentation> userRepresentations) {
        return Optional.ofNullable(userRepresentations).orElse(Collections.emptyList()).stream()
                .map(this::mapUser).toList();

    }
}
