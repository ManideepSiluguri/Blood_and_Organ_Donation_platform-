package com.example.bloodplatform.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "emergency_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmergencyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hospitalName;
    private String contactPerson;
    private String contactPhone;
    private String contactEmail;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    @Enumerated(EnumType.STRING)
    private OrganType organType;

    private Integer unitsRequired;
    private String city;
    private String state;
    private String notes;

    private LocalDateTime createdAt;

    private boolean resolved;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
