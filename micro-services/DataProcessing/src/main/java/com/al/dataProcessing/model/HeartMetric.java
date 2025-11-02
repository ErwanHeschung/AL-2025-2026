package com.al.dataProcessing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "heart_metrics")
public class HeartMetric extends BaseMetric {

    @Column(nullable = false)
    private Double value;
}