package com.al.dataProcessing.model;
import java.time.Instant;

public interface HourlyAverage {
    Instant getHour();
    Double getAverage();
}