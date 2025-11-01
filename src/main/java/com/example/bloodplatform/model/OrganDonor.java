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
@Table(name = "organ_donor")
public class OrganDonor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank private String donorName;
    @NotBlank private String organType; // kidney, heart, liver, etc.
    @Email private String email;
    @Pattern(regexp = "^[0-9]{10}$") private String phone;
    @NotBlank private String bloodGroup;
    @Min(18) private Integer age;
    private String city;
    private String status; // PENDING, MATCHED, TRANSPLANTED
    private LocalDateTime registrationDate = LocalDateTime.now();
}
