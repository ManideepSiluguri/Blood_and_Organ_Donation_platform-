package com.example.bloodplatform.dto;

import lombok.Data;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
public class OrganDonorResponseDto {
    private UUID id;
    private String donorName;
    private String organType;
    private String email;
    private String phone;
    private String bloodGroup;
    private Integer age;
    private String city;
    private String status;
    private LocalDateTime registrationDate;
}
