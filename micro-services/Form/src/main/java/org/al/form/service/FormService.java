package org.al.form.service;

import lombok.RequiredArgsConstructor;
import org.al.form.dto.FormRequest;
import org.al.form.dto.FormResponse;
import org.al.form.entities.Form;
import org.al.form.exceptions.FormNotFoundException;
import org.al.form.repositories.FormRepository;
import org.al.form.serviceinterface.IFormService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FormService implements IFormService {

    private final FormRepository repository;

    @Override
    public FormResponse createForm(FormRequest request) {
        Form form = toEntity(request);
        Form saved = repository.save(form);
        return toResponse(saved);
    }

    @Override
    public FormResponse getFormById(UUID id) {
        Form form = repository.findById(id)
                .orElseThrow(() -> new FormNotFoundException("Form not found: " + id));
        return toResponse(form);
    }

    @Override
    public List<FormResponse> getFormsByPatient(UUID patientId, int limit) {
        return repository.findTopByPatientId(patientId, limit).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<FormResponse> getFormsByIssuer(UUID issuerId, int limit) {
        return repository.findTopByIssuerId(issuerId, limit).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public void deleteForm(UUID id) {
        if (!repository.existsById(id)) {
            throw new FormNotFoundException("Form not found: " + id);
        }
        repository.deleteById(id);
    }


    private Form toEntity(FormRequest request) {
        Form form = new Form();
        form.setPatientId(request.getPatientId());
        form.setIssuerId(request.getIssuerId());
        form.setData(request.getData());
        form.setType(request.getType());
        return form;
    }

    private FormResponse toResponse(Form form) {
        return FormResponse.builder()
                .id(form.getId())
                .patientId(form.getPatientId())
                .issuerId(form.getIssuerId())
                .data(form.getData())
                .type(form.getType())
                .build();
    }
}