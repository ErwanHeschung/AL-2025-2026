package org.al.patientmanagement.service;

import lombok.RequiredArgsConstructor;
import org.al.patientmanagement.clients.ProcessDataClient;
import org.al.patientmanagement.clients.UserClient;
import org.al.patientmanagement.dto.PatientDTO;
import org.al.patientmanagement.dto.PatientRecordSummaryDTO;
import org.al.patientmanagement.serviceinterface.IPatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService implements IPatientService {

    private final UserClient userClient;
    private final ProcessDataClient processDataClient;

    @Override
    public List<PatientDTO> getAllPatientsForDoctor(UUID doctorId) {
        return userClient.getUsersByDoctorId(doctorId);
    }

    @Override
    public PatientDTO getPatientInformation(UUID patientId) {
        return userClient.getUserById(patientId);
    }

    @Override
    public List<PatientRecordSummaryDTO> getPatientRecords(UUID patientId, LocalDate startDate, LocalDate endDate, int limit) {
        PatientDTO patient = userClient.getUserById(patientId);
        if (patient.getBraceletId() == null) {
            return List.of();
        }
        return processDataClient.getPatientRecords(patient.getBraceletId(), startDate, endDate, limit);
    }
}
