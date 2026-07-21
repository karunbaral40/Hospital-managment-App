package com.hospital.Hospital.Management.System.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request payload for registering a new doctor")
public class DoctorRequestDTO {

    @NotBlank(message = "Doctor name cannot be empty")
    @Size(min = 2, max = 100, message = "Name must be 2 to 100 characters")
    @Schema(description = "Doctor's full name", example = "Dr. Smith", minLength = 2, maxLength = 100)
    private String name;

    @NotBlank(message = "Specialization cannot be empty")
    @Schema(description = "Medical specialization", example = "Cardiology")
    private String specialization;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Please provide valid email")
    @Schema(description = "Doctor's email address", example = "dr.smith@hospital.com")
    private String email;

    @NotBlank(message = "Phone cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    @Schema(description = "Doctor's phone number (10 digits)", example = "9876543210", pattern = "^[0-9]{10}$")
    private String phone;

    @Schema(description = "Whether the doctor is available for appointments", example = "true")
    private Boolean available = true;

    public DoctorRequestDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}