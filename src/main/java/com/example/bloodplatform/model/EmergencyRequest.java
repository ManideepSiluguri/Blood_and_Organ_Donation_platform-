package com.example.bloodplatform.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "emergency_request")
public class EmergencyRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String description;
    private String location;

    @Pattern(regexp = "^[0-9]{10}$")
    private String contactPhone;

    private String bloodGroupNeeded;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String status; // NEW, RESPONDED, CLOSED
    private String responder; // username or phone who claimed
}
