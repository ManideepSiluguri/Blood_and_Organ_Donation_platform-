package com.example.bloodplatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipient")
public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotBlank private String name;
    @Email private String email;
    @Pattern(regexp="^[0-9]{10}$") private String phone;
    private String bloodGroupNeeded;
    private String hospital;
    private String city;
    private LocalDateTime requestDate = LocalDateTime.now();
    private String status; // OPEN, FULFILLED, CANCELLED
}
