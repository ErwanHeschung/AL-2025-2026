package org.al.patientmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.al.patientmanagement.enums.Activity;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRecordSummaryDTO {
    private Instant timestamp;
    private double bpm;
    private double bloodOxygen;
    private Activity activity;
}
