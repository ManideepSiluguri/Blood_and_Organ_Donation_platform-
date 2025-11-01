package com.example.bloodplatform.mapper;

import com.example.bloodplatform.dto.BloodDonorRequestDto;
import com.example.bloodplatform.dto.BloodDonorResponseDto;
import com.example.bloodplatform.model.BloodDonor;

public class BloodDonorMapper {
    public static BloodDonorResponseDto toDto(BloodDonor d) {
        if (d == null) return null;
        BloodDonorResponseDto dto = new BloodDonorResponseDto();
        dto.setId(d.getId());
        dto.setFullName(d.getFirstName() + " " + d.getLastName());
        dto.setEmail(d.getEmail());
        dto.setPhone(d.getPhone());
        dto.setBloodGroup(d.getBloodGroup());
        dto.setActive(d.getActive());
        dto.setLastDonationDate(d.getLastDonationDate());
        return dto;
    }

    public static BloodDonor fromDto(BloodDonorRequestDto req) {
        BloodDonor d = new BloodDonor();
        d.setFirstName(req.getFirstName());
        d.setLastName(req.getLastName());
        d.setEmail(req.getEmail());
        d.setPhone(req.getPhone());
        d.setBloodGroup(req.getBloodGroup());
        d.setAge(req.getAge());
        d.setGender(req.getGender());
        d.setAddress(req.getAddress());
        return d;
    }
}
