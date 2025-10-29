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
        //TODO retrive from patient data and map bracelet id to patient ID;
        return List.of();
    }
}
