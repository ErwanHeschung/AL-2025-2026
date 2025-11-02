package com.al.dataProcessing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blood_oxygen_metrics")
public class BloodOxygenMetric extends BaseMetric {

    @Column(nullable = false)
    private Integer value;
}