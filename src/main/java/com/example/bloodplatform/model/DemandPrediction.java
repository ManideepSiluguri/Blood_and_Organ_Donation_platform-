package com.example.bloodplatform.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "demand_prediction")
public class DemandPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate predictionDate;
    private String bloodGroup;
    private Integer predictedDemand; // units needed
    private String riskLevel; // LOW, MEDIUM, HIGH, CRITICAL
    private String reason; // Holiday, Weather, Festival, etc.
}
