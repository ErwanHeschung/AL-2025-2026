package org.al.patientmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String braceletId;
    private UUID doctorId;
}
