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
    private int bpm;
    private double sp02;
    private Activity activity;
}
