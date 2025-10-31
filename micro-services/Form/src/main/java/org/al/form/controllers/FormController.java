package org.al.form.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.al.form.dto.FormRequest;
import org.al.form.dto.FormResponse;
import org.al.form.serviceinterface.IFormService;
import org.springframework.web.bind.annotation.*;

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