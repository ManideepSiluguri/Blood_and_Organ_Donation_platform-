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
@Table(name = "donor")
public class Donor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotBlank private String firstName;
    @NotBlank private String lastName;
    @Email @Column(unique = true) private String email;
    @Pattern(regexp = "^[0-9]{10}$") private String phone;
    @NotBlank private String bloodGroup;
    @Min(18) private Integer age;
    private String gender;
    private String address;
    private Boolean active = true;
    private LocalDateTime lastDonationDate;
}
