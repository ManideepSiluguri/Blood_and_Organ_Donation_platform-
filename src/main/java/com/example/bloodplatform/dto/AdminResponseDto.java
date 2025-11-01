package com.example.bloodplatform.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class AdminResponseDto {
    private UUID id;
    private String username;
    private String email;
    private String role;
}
