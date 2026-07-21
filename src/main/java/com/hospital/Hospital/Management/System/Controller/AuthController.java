package com.hospital.Hospital.Management.System.Controller;

import com.hospital.Hospital.Management.System.Services.JwtService;

import com.hospital.Hospital.Management.System.model.LoginRequest;
import com.hospital.Hospital.Management.System.model.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Login to get JWT token")
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login",
            description = "Send username and password, get JWT token back. " +
                    "Use this token in all other requests as Bearer token."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful, JWT token returned"),
            @ApiResponse(responseCode = "400", description = "Invalid username or password"),
            @ApiResponse(responseCode = "401", description = "Authentication failed")
    })
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest) {
        try {
            // 1. Authenticate — throws exception if wrong credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // 2. Get user details from authenticated object
            UserDetails userDetails =
                    (UserDetails) authentication.getPrincipal();

            // 3. Generate JWT token
            String token = jwtService.generateToken(userDetails);

            // 4. Return token
            return ResponseEntity.ok(
                    new LoginResponse(token, userDetails.getUsername()));

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}