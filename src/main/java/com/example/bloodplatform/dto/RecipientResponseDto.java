package com.example.bloodplatform.dto;

import lombok.Data;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
public class RecipientResponseDto {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String bloodGroupNeeded;
    private String hospital;
    private String city;
    private LocalDateTime requestDate;
    private String status;
}
