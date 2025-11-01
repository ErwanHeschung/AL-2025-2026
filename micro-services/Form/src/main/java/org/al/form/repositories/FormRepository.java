package org.al.form.repositories;

import org.al.form.entities.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FormRepository extends JpaRepository<Form, UUID> {
    @Query(value = "SELECT * FROM forms WHERE patient_id = :patientId LIMIT :limit", nativeQuery = true)
    List<Form> findTopByPatientId(@Param("patientId") UUID patientId, @Param("limit") int limit);

    @Query(value = "SELECT * FROM forms WHERE issuer_id = :issuerId LIMIT :limit", nativeQuery = true)
    List<Form> findTopByIssuerId(@Param("issuerId") UUID issuerId, @Param("limit") int limit);

    Optional<Form> findByPatientIdAndDate(UUID patientId, LocalDate date);
}
