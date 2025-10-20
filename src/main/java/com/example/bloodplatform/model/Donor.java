package com.example.bloodplatform.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="donors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    @Column(unique = true)
    private String  email;
    private String phone;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private BloodType bloodType;
    private boolean available;
    private LocalDate lastDonationDate;
    private boolean isOrganDonor;
    @Embedded
    private Location location;
    private String notes;
}
