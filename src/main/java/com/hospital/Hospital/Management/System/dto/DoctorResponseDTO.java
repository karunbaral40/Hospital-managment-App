package com.hospital.Hospital.Management.System.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response payload containing doctor information")
public class DoctorResponseDTO {

    @Schema(description = "Unique doctor identifier", example = "1")
    private Integer id;
    
    @Schema(description = "Doctor's full name", example = "Dr. Smith")
    private String name;
    
    @Schema(description = "Medical specialization", example = "Cardiology")
    private String specialization;
    
    @Schema(description = "Doctor's email address", example = "dr.smith@hospital.com")
    private String email;
    
    @Schema(description = "Doctor's phone number", example = "9876543210")
    private String phone;
    
    @Schema(description = "Whether the doctor is available for appointments", example = "true")
    private Boolean available;

    public DoctorResponseDTO() {}

    public DoctorResponseDTO(Integer id, String name, String specialization, String email, String phone, Boolean available) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
        this.available = available;
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