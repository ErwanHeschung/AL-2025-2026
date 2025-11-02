package com.al.dataProcessing.repository;

import com.al.dataProcessing.model.AccelerometerMetric;
import com.al.dataProcessing.model.HourlyActivity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AccelerometerRepository extends JpaRepository<AccelerometerMetric, UUID> {

    @Query(value = "SELECT " +
            "    time_bucket('1 hour', timestamp) AS hour, " +
            "    STDDEV(SQRT(x*x + y*y + z*z)) AS activityMetric " +
            "FROM accelerometer_metrics " +
            "WHERE bracelet_id = :braceletId " +
            "  AND timestamp >= :start AND timestamp < :end " +
            "GROUP BY hour " +
            "ORDER BY hour",
            nativeQuery = true)
    List<HourlyActivity> findHourlyActivity(String braceletId, OffsetDateTime start, OffsetDateTime end);
}