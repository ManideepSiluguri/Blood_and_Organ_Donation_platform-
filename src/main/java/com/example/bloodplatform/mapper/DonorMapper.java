package com.example.bloodplatform.mapper;

import com.example.bloodplatform.dto.DonorDto;
import com.example.bloodplatform.model.Donor;

public class DonorMapper {
    public static DonorDto toDto(Donor d){
        if (d == null) return null;
        DonorDto dto = new DonorDto();
        dto.setId(d.getId());
        dto.setFullName((d.getFirstName() == null ? "" : d.getFirstName()) + " " + (d.getLastName() == null ? "" : d.getLastName()));
        dto.setEmail(d.getEmail());
        dto.setPhone(d.getPhone());
        dto.setBloodGroup(d.getBloodGroup());
        return dto;
    }
}
