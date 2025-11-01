package com.example.bloodplatform.dto;

import lombok.Data;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
public class BloodDonorResponseDto {
    private UUID id;
    private String fullName;
    private String email;
    private String phone;
    private String bloodGroup;
    private Boolean active;
    private LocalDateTime lastDonationDate;
}
