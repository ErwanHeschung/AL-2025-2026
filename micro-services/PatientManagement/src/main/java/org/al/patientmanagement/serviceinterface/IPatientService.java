package org.al.patientmanagement.serviceinterface;

import org.al.patientmanagement.dto.PatientDTO;
import org.al.patientmanagement.dto.PatientRecordSummaryDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IPatientService {

    List<PatientDTO> getAllPatientsForDoctor(UUID doctorId);

    PatientDTO getPatientInformation(UUID patientId);

    List<PatientRecordSummaryDTO> getPatientRecords(UUID patientId, LocalDate startDate, LocalDate endDate, int limit);
}