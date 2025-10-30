package com.al.dataProcessing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "heart_metrics")
public class HeartMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private OffsetDateTime timestamp;

    @Column(nullable = false)
    private String braceletId;

    @Column(nullable = false)
    private Double value;
}