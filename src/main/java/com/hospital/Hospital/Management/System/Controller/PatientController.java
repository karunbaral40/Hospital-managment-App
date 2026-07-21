package com.hospital.Hospital.Management.System.Controller;

import com.hospital.Hospital.Management.System.Services.PatientServices;
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
@RequestMapping("/patients")
@Tag(name = "Patients", description = "Manage hospital patients")
public class PatientController {

    private final PatientServices patientServices;
    private final PatientMapper patientMapper;

    public PatientController(PatientServices patientServices,
                             PatientMapper patientMapper) {
        this.patientServices = patientServices;
        this.patientMapper = patientMapper;
    }

    @GetMapping
    @Operation(summary = "Get all patients",
            description = "Returns all patients with their assigned doctor name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all patients"),
            @ApiResponse(responseCode = "401", description = "Not authenticated")
    })
    public ResponseEntity<List<PatientResponseDTO>> getAll() {
        return ResponseEntity.ok(
                patientMapper.toResponseDTOList(
                        patientServices.findAll()));
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Get patient by ID",
            description = "Returns a specific patient by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient found"),
            @ApiResponse(responseCode = "404", description = "Patient not found"),
            @ApiResponse(responseCode = "401", description = "Not authenticated")
    })
    public ResponseEntity<PatientResponseDTO> getById(
            @Parameter(description = "Patient's unique ID", example = "1")
            @PathVariable int id) {
        return ResponseEntity.ok(
                patientMapper.toResponseDTO(
                        patientServices.findById(id)));
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get patient by name",
            description = "Returns a specific patient by their name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient found"),
            @ApiResponse(responseCode = "404", description = "Patient not found"),
            @ApiResponse(responseCode = "401", description = "Not authenticated")
    })
    public ResponseEntity<PatientResponseDTO> getByName(
            @Parameter(description = "Patient's full name", example = "John Doe")
            @PathVariable String name) {
        return ResponseEntity.ok(
                patientMapper.toResponseDTO(
                        patientServices.findByName(name)));
    }

    @GetMapping("/doctor/{doctorId}")
    @Operation(summary = "Get patients by doctor",
            description = "Returns all patients assigned to a specific doctor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved patients"),
            @ApiResponse(responseCode = "404", description = "Doctor not found"),
            @ApiResponse(responseCode = "401", description = "Not authenticated")
    })
    public ResponseEntity<List<PatientResponseDTO>> getByDoctor(
            @Parameter(description = "Doctor's unique ID", example = "1")
            @PathVariable int doctorId) {
        return ResponseEntity.ok(
                patientMapper.toResponseDTOList(
                        patientServices.findByDoctorId(doctorId)));
    }

    @GetMapping("/serious/{serious}")
    @Operation(summary = "Get patients by severity",
            description = "Returns all patients filtered by seriousness of condition")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved patients"),
            @ApiResponse(responseCode = "401", description = "Not authenticated")
    })
    public ResponseEntity<List<PatientResponseDTO>> getBySerious(
            @Parameter(description = "Filter by serious condition status", example = "false")
            @PathVariable Boolean serious) {
        return ResponseEntity.ok(
                patientMapper.toResponseDTOList(
                        patientServices.findBySerious(serious)));
    }

    @PostMapping
    @Operation(summary = "Admit new patient",
            description = "Admits a patient and assigns to a doctor. " +
                    "Pass doctorId as query param. Requires ADMIN role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Patient admitted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid patient data"),
            @ApiResponse(responseCode = "404", description = "Doctor not found"),
            @ApiResponse(responseCode = "401", description = "Not authenticated"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions (ADMIN required)")
    })
    public ResponseEntity<PatientResponseDTO> save(
            @Valid @RequestBody PatientRequestDTO dto,
            @Parameter(description = "Doctor ID to assign patient", example = "1")
            @RequestParam Integer doctorId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(patientMapper.toResponseDTO(
                        patientServices.savePatient(
                                patientMapper.toEntity(dto), doctorId)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update patient",
            description = "Updates an existing patient's information. Requires ADMIN role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid patient data"),
            @ApiResponse(responseCode = "404", description = "Patient not found"),
            @ApiResponse(responseCode = "401", description = "Not authenticated"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions (ADMIN required)")
    })
    public ResponseEntity<PatientResponseDTO> update(
            @Parameter(description = "Patient's unique ID", example = "1")
            @PathVariable int id,
            @Valid @RequestBody PatientRequestDTO dto) {
        return ResponseEntity.ok(
                patientMapper.toResponseDTO(
                        patientServices.updateByPatientId(id,
                                patientMapper.toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete patient",
            description = "Deletes a patient record. Requires ADMIN role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Patient not found"),
            @ApiResponse(responseCode = "401", description = "Not authenticated"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions (ADMIN required)")
    })
    public ResponseEntity<String> delete(
            @Parameter(description = "Patient's unique ID", example = "1")
            @PathVariable int id) {
        return ResponseEntity.ok(
                patientServices.deletePatientById(id));
    }
}