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
@Table(name = "blood_donor")
public class BloodDonor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank private String firstName;
    @NotBlank private String lastName;
    @Email private String email;
    @Pattern(regexp = "^[0-9]{10}$") private String phone;
    @NotBlank private String bloodGroup; // A+, A-, B+, B-, AB+, AB-, O+, O-
    @Min(18) private Integer age;
    private String gender;
    private String address;
    private Boolean active = true;
    private LocalDateTime lastDonationDate;
}
