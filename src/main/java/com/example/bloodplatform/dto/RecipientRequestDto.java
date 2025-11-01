package com.example.bloodplatform.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RecipientRequestDto {
    @NotBlank private String name;
    @Email private String email;
    @Pattern(regexp="^[0-9]{10}$") private String phone;
    @NotBlank private String bloodGroupNeeded;
    private String hospital;
    private String city;
}
