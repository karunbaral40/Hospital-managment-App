package com.hospital.Hospital.Management.System.dto;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response payload containing patient information with assigned doctor")
public class PatientResponseDTO {

    @Schema(description = "Unique patient identifier", example = "1")
    private Integer id;
    
    @Schema(description = "Patient's full name", example = "John Doe")
    private String name;
    
    @Schema(description = "Patient's email address", example = "john@example.com")
    private String email;
    
    @Schema(description = "Patient's age in years", example = "35")
    private Integer age;
    
    @Schema(description = "Patient's primary disease or condition", example = "Diabetes")
    private String disease;
    
    @Schema(description = "Date of admission", example = "2024-07-21", format = "date")
    private LocalDate admitDate;
    
    @Schema(description = "Whether the patient's condition is serious", example = "false")
    private Boolean serious;
    
    @Schema(description = "Name of the assigned doctor", example = "Dr. Smith")
    private String doctorName;

    public PatientResponseDTO() {}

    public PatientResponseDTO(Integer id, String name, String email, Integer age, String disease, LocalDate admitDate, Boolean serious, String doctorName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.disease = disease;
        this.admitDate = admitDate;
        this.serious = serious;
        this.doctorName = doctorName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}