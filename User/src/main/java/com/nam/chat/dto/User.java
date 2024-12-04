package com.nam.chat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

}
