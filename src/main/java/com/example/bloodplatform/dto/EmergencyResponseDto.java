package com.example.bloodplatform.dto;

import lombok.Data;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
public class EmergencyResponseDto {
    private UUID id;
    private String description;
    private String location;
    private String contactPhone;
    private String bloodGroupNeeded;
    private LocalDateTime createdAt;
    private String status;
    private String responder;
}
