package org.al.patientmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "doctor_patient")
@IdClass(DoctorPatientId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorPatientLink {
    @Id
    private UUID doctorId;

    @Id
    private UUID patientId;
}