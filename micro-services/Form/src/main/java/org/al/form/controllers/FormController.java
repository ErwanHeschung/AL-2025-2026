package org.al.form.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.al.form.dto.FormRequest;
import org.al.form.dto.FormResponse;
import org.al.form.serviceinterface.IFormService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/forms")
@RequiredArgsConstructor
public class FormController {

    private final IFormService service;

    @PostMapping
    public FormResponse createForm(@Valid @RequestBody FormRequest request) {
        return service.createForm(request);
    }

    @GetMapping("/{id}")
    public FormResponse getFormById(@PathVariable UUID id) {
        return service.getFormById(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<FormResponse> getFormsByPatient(
            @PathVariable UUID patientId,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return service.getFormsByPatient(patientId, limit);
    }

    @GetMapping("/patient-date/{patientId}/{date}")
    public ResponseEntity<FormResponse> getFormByPatientAndDate(
            @PathVariable UUID patientId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return service.getFormByPatientAndDate(patientId, date)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.ok().build());  // Return 200 with empty body instead of 404
    }

    @GetMapping("/issuer/{issuerId}")
    public List<FormResponse> getFormsByIssuer(
            @PathVariable UUID issuerId,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return service.getFormsByIssuer(issuerId, limit);
    }

    @DeleteMapping("/{id}")
    public void deleteForm(@PathVariable UUID id) {
        service.deleteForm(id);
    }
}