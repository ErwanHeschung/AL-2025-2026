package com.al.dataProcessing.repository;

import com.al.dataProcessing.model.HeartMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeartMetricRepository extends JpaRepository<HeartMetric, Long> {
}