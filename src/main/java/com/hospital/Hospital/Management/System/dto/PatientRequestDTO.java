package com.hospital.Hospital.Management.System.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request payload for admitting a new patient")
public class PatientRequestDTO {

    @NotBlank(message = "Name must not be empty")
    @Size(min = 2, max = 100, message = "Name must be 2 to 100 characters")
    @Schema(description = "Patient's full name", example = "John Doe", minLength = 2, maxLength = 100)
    private String name;

    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email must be valid")
    @Schema(description = "Patient's email address", example = "john@example.com")
    private String email;

    @Min(value = 1, message = "Age must be at least 1")
    @Max(value = 120, message = "Age cannot exceed 120")
    @Schema(description = "Patient's age in years", example = "35", minimum = "1", maximum = "120")
    private Integer age;

    @NotBlank(message = "Disease cannot be empty")
    @Schema(description = "Patient's primary disease or condition", example = "Diabetes")
    private String disease;

    @NotNull(message = "Admit date cannot be null")
    @Schema(description = "Date of admission", example = "2024-07-21", format = "date")
    private LocalDate admitDate;

    @Schema(description = "Whether the patient's condition is serious", example = "false")
    private Boolean serious = false;

    public PatientRequestDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public LocalDate getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(LocalDate admitDate) {
        this.admitDate = admitDate;
    }

    public Boolean getSerious() {
        return serious;
    }

    public void setSerious(Boolean serious) {
        this.serious = serious;
    }
}