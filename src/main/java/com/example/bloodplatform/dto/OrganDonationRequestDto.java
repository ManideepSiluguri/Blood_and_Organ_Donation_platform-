package com.example.bloodplatform.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class OrganDonationRequestDto {
    @NotBlank private String organType;
    @NotBlank private String donorName;
    @NotBlank private String recipientName;
}
