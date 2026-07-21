package com.hospital.Hospital.Management.System.Controller;

import com.hospital.Hospital.Management.System.Services.DoctorServices;
import com.hospital.Hospital.Management.System.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doctors")
@Tag(name = "Doctors", description = "Manage hospital doctors")
public class DoctorController {


    private final DoctorServices doctorServices;
    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;

    public DoctorController(DoctorServices doctorServices,
                            DoctorMapper doctorMapper,
                            PatientMapper patientMapper) {
        this.doctorServices = doctorServices;
        this.doctorMapper = doctorMapper;
        this.patientMapper = patientMapper;
    }

    @GetMapping
    @Operation(summary = "Get all doctors",
            description = "Returns list of all doctors without patient details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all doctors"),
            @ApiResponse(responseCode = "401", description = "Not authenticated")
    })
    public ResponseEntity<List<DoctorResponseDTO>> findAll() {
        return ResponseEntity.ok(
                doctorMapper.toResponseDTOList(
                        doctorServices.findAllDoctor()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get doctor by ID",
            description = "Returns doctor with their full patient list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor found"),
            @ApiResponse(responseCode = "404", description = "Doctor not found"),
            @ApiResponse(responseCode = "401", description = "Not authenticated")
    })
    public ResponseEntity<DoctorWithPatientsDTO> getById(
            @Parameter(description = "Doctor's unique ID", example = "1")
            @PathVariable Integer id) {
        return ResponseEntity.ok(
                doctorMapper.toDoctorWithPatientsDTO(
                        doctorServices.getDoctorById(id), patientMapper));
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get doctors by name",
            description = "Returns doctors matching the given name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved doctors"),
            @ApiResponse(responseCode = "404", description = "Doctor not found"),
            @ApiResponse(responseCode = "401", description = "Not authenticated")
    })
    public ResponseEntity<List<DoctorResponseDTO>> getByName(
            @Parameter(description = "Doctor's full name", example = "Dr. Smith")
            @PathVariable String name) {
        return ResponseEntity.ok(
                doctorMapper.toResponseDTOList(
                        doctorServices.findByName(name)));
    }

    @GetMapping("/specialization/{specialization}")
    @Operation(summary = "Get doctors by specialization",
            description = "Returns all doctors with a specific specialization")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved doctors"),
            @ApiResponse(responseCode = "401", description = "Not authenticated")
    })
    public ResponseEntity<List<DoctorResponseDTO>> getBySpecialization(
            @Parameter(description = "Medical specialization", example = "Cardiology")
            @PathVariable String specialization) {
        return ResponseEntity.ok(
                doctorMapper.toResponseDTOList(
                        doctorServices.findBySpecialization(specialization)));
    }

    @GetMapping("/available/{available}")
    @Operation(summary = "Get available doctors",
            description = "Returns doctors filtered by availability status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved doctors"),
            @ApiResponse(responseCode = "401", description = "Not authenticated")
    })
    public ResponseEntity<List<DoctorResponseDTO>> getAvailable(
            @Parameter(description = "Filter by availability status", example = "true")
            @PathVariable Boolean available) {
        return ResponseEntity.ok(
                doctorMapper.toResponseDTOList(
                        doctorServices.getAvailableDoctors(available)));
    }

    @PostMapping
    @Operation(summary = "Add new doctor",
            description = "Creates a new doctor. Requires ADMIN role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doctor created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid doctor data"),
            @ApiResponse(responseCode = "401", description = "Not authenticated"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions (ADMIN required)"),
            @ApiResponse(responseCode = "409", description = "Doctor already exists")
    })
    public ResponseEntity<DoctorResponseDTO> save(
            @Valid @RequestBody DoctorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(doctorMapper.toResponseDTO(
                        doctorServices.saveDoctor(
                                doctorMapper.toEntity(dto))));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update doctor",
            description = "Updates existing doctor information. Requires ADMIN role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid doctor data"),
            @ApiResponse(responseCode = "404", description = "Doctor not found"),
            @ApiResponse(responseCode = "401", description = "Not authenticated"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions (ADMIN required)")
    })
    public ResponseEntity<DoctorResponseDTO> update(
            @Parameter(description = "Doctor's unique ID", example = "1")
            @PathVariable Integer id,
            @Valid @RequestBody DoctorRequestDTO dto) {
        return ResponseEntity.ok(
                doctorMapper.toResponseDTO(
                        doctorServices.updateDoctor(id,
                                doctorMapper.toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete doctor",
            description = "Deletes a doctor if no patients are assigned. Requires ADMIN role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Cannot delete doctor with assigned patients"),
            @ApiResponse(responseCode = "404", description = "Doctor not found"),
            @ApiResponse(responseCode = "401", description = "Not authenticated"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions (ADMIN required)")
    })
    public ResponseEntity<String> deleteById(
            @Parameter(description = "Doctor's unique ID", example = "1")
            @PathVariable Integer id) {
        return ResponseEntity.ok(
                doctorServices.deleteDoctorById(id));
    }
}