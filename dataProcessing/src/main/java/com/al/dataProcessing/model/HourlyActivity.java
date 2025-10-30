package com.al.dataProcessing.model;
import java.time.Instant;

public interface HourlyActivity {
    Instant getHour();
    Double getActivityMetric();
}