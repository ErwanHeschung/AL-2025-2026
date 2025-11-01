package org.al.form.serviceinterface;

import org.al.form.dto.FormRequest;
import org.al.form.dto.FormResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IFormService {

    FormResponse createForm(FormRequest request);

    FormResponse getFormById(UUID id);

    List<FormResponse> getFormsByPatient(UUID patientId, int limit);

    Optional<FormResponse> getFormByPatientAndDate(UUID patientId, LocalDate date);

    List<FormResponse> getFormsByIssuer(UUID issuerId, int limit);

    void deleteForm(UUID id);
}

