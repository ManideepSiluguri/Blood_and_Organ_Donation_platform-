package com.example.bloodplatform.dto;

import lombok.Data;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
public class OrganDonationResponseDto {
    private UUID id;
    private String organType;
    private String donorName;
    private String recipientName;
    private String status;
    private LocalDateTime createdAt;
}
