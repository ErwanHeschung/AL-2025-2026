package org.al.patientmanagement.repositories;


import org.al.patientmanagement.entities.DoctorPatientLink;
import org.al.patientmanagement.entities.DoctorPatientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorPatientRepository extends JpaRepository<DoctorPatientLink, DoctorPatientId> {
    List<DoctorPatientLink> findByDoctorId(UUID doctorId);

    List<DoctorPatientLink> findByPatientId(UUID patientId);
}
