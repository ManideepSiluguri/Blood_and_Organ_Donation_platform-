package com.example.bloodplatform.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BloodDonorRequestDto {
    @NotBlank private String firstName;
    @NotBlank private String lastName;
    @Email private String email;
    @Pattern(regexp = "^[0-9]{10}$") private String phone;
    @NotBlank private String bloodGroup;
    @Min(18) private Integer age;
    private String gender;
    private String address;
}
