package com.example.bloodplatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "organ_donation")
public class OrganDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String organType; // kidney, heart, etc.

    @NotBlank
    private String donorName;

    @NotBlank
    private String recipientName;

    private String status; // PENDING, COMPLETED, MATCHED
    private LocalDateTime createdAt = LocalDateTime.now();
}
