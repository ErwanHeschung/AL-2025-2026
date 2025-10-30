package com.al.dataProcessing.repository;

import com.al.dataProcessing.model.AccelerometerMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccelerometerRepository extends JpaRepository<AccelerometerMetric, UUID> {
}