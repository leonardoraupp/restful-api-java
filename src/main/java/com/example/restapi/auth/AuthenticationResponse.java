package com.example.restapi.auth;

import org.springframework.lang.NonNull;


public class AuthenticationResponse {
    @NonNull
    private String token;

    private AuthenticationResponse(Builder builder) {
        this.token = builder.token;
    }

    public String getToken() {
        return token;
    }

    public static class Builder {
        private String token;

        public Builder setToken(@NonNull String token) {
            this.token = token;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(this);
        }
    }
}
