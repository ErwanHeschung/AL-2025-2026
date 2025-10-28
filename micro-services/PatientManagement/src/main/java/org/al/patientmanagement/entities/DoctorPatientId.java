package org.al.patientmanagement.entities;

import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorPatientId {
    private UUID doctorId;
    private UUID patientId;
}