package com.hospital.Hospital.Management.System.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response payload containing JWT token after successful authentication")
public class LoginResponse {
    
    @Schema(description = "JWT token for subsequent API requests", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;
    
    @Schema(description = "Token type", example = "Bearer")
    private String type = "Bearer";
    
    @Schema(description = "Username of authenticated user", example = "admin")
    private String username;

    public LoginResponse() {}

    public LoginResponse(String token, String username) {
        this.token = token;
        this.type = "Bearer";
        this.username = username;
    }

    public LoginResponse(String token, String type, String username) {
        this.token = token;
        this.type = type;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}