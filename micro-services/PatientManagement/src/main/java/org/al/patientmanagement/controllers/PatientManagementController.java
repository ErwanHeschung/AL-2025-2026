package org.al.patientmanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.al.patientmanagement.dto.PatientDTO;
import org.al.patientmanagement.dto.PatientRecordDTO;
import org.al.patientmanagement.dto.PatientRecordSummaryDTO;
import org.al.patientmanagement.serviceinterface.IPatientService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientManagementController {

    private final IPatientService patientService;

    @GetMapping
    public List<PatientDTO> getAllPatients(@RequestHeader("X-User-Id") UUID doctorId) {
        return patientService.getAllPatientsForDoctor(doctorId);
    }

    @GetMapping("/{patientId}")
    public PatientDTO getPatientInformation(@PathVariable UUID patientId) {
        return patientService.getPatientInformation(patientId);
    }

    @GetMapping("/{patientId}/records")
    public List<PatientRecordSummaryDTO> getPatientRecords(
            @PathVariable UUID patientId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(defaultValue = "50") int limit) {
        return patientService.getPatientRecords(patientId, startDate, endDate, limit);
    }

}
