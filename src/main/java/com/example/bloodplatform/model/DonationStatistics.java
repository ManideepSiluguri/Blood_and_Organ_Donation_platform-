package com.example.bloodplatform.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "donation_statistics")
public class DonationStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;
    private String bloodGroup;
    private Integer donationCount;
    private Integer requestCount;
    private Integer matchedCount;
    private Double averageResponseTime; // in minutes
}
