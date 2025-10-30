package com.al.dataProcessing.repository;

import com.al.dataProcessing.model.BloodOxygenMetric;
import com.al.dataProcessing.model.HourlyAverage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface BloodOxygenRepository extends JpaRepository<BloodOxygenMetric, UUID> {

    @Query(value = "SELECT time_bucket('1 hour', timestamp) AS hour, AVG(value) AS average " +
            "FROM blood_oxygen_metrics " +
            "WHERE bracelet_id = :braceletId " +
            "  AND timestamp >= :start AND timestamp < :end " +
            "GROUP BY hour ORDER BY hour",
            nativeQuery = true)
    List<HourlyAverage> findHourlyAverages(String braceletId, OffsetDateTime start, OffsetDateTime end);
}