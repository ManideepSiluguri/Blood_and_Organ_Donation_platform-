package com.example.bloodplatform.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmergencyRequestDto {
    @NotBlank private String description;
    @NotBlank private String location;
    @Pattern(regexp="^[0-9]{10}$") private String contactPhone;
    @NotBlank private String bloodGroupNeeded;
}
