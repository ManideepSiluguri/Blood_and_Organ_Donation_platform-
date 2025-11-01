package com.example.bloodplatform.mapper;

import com.example.bloodplatform.dto.DonorRequestDto;
import com.example.bloodplatform.dto.DonorResponseDto;
import com.example.bloodplatform.model.Donor;

public class DonorMapper {
    public static DonorResponseDto toDto(Donor d) {
        if (d == null) return null;
        DonorResponseDto dto = new DonorResponseDto();
        dto.setId(d.getId());
        dto.setFullName(d.getFirstName() + " " + d.getLastName());
        dto.setEmail(d.getEmail());
        dto.setPhone(d.getPhone());
        dto.setBloodGroup(d.getBloodGroup());
        dto.setActive(d.getActive());
        dto.setLastDonationDate(d.getLastDonationDate());
        return dto;
    }

    public static Donor fromDto(DonorRequestDto req) {
        Donor d = new Donor();
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
