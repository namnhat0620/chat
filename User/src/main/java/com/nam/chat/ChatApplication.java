package com.nam.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@SecurityScheme(name = "Keycloak",
		openIdConnectUrl = "http://localhost:9080/realms/chat/.well-known/openid-configuration",
		scheme = "bearer", type = SecuritySchemeType.OPENIDCONNECT, in = SecuritySchemeIn.HEADER)
@EnableJpaRepositories(basePackages = "com.nam.chat.repository")
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

}
