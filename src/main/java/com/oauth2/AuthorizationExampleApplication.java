package com.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class AuthorizationExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationExampleApplication.class, args);
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory()
	        .withClient("my-trusted-client")
            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
            .scopes("read", "write", "trust")
            .secret("secret")
            .accessTokenValiditySeconds(120).//Access token is only valid for 2 minutes.
            refreshTokenValiditySeconds(600);//Refresh token is only valid for 10 minutes.
	}
}

