package com.example.bloodplatform.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AdminRequestDto {
    @NotBlank private String username;
    @Email private String email;
    @NotBlank private String role;
}
