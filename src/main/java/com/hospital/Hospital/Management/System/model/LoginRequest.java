package com.hospital.Hospital.Management.System.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request payload for user login authentication")
public class LoginRequest {
    
    @Schema(description = "Username or email for authentication", example = "admin")
    private String username;
    
    @Schema(description = "Password for authentication", example = "password")
    private String password;

    public LoginRequest() {}

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
