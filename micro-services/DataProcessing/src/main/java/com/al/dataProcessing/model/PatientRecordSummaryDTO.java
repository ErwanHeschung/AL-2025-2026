package com.al.dataProcessing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRecordSummaryDTO {
    private Instant timestamp;
    private double bpm;
    private double bloodOxygen;
    private Activity activity;

    public PatientRecordSummaryDTO(Instant timestamp) {
        this.timestamp = timestamp;
    }
}