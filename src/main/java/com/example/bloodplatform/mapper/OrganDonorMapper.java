package com.example.bloodplatform.mapper;

import com.example.bloodplatform.dto.OrganDonorRequestDto;
import com.example.bloodplatform.dto.OrganDonorResponseDto;
import com.example.bloodplatform.model.OrganDonor;

public class OrganDonorMapper {
    public static OrganDonorResponseDto toDto(OrganDonor o) {
        if (o == null) return null;
        OrganDonorResponseDto dto = new OrganDonorResponseDto();
        dto.setId(o.getId());
        dto.setDonorName(o.getDonorName());
        dto.setOrganType(o.getOrganType());
        dto.setEmail(o.getEmail());
        dto.setPhone(o.getPhone());
        dto.setBloodGroup(o.getBloodGroup());
        dto.setAge(o.getAge());
        dto.setCity(o.getCity());
        dto.setStatus(o.getStatus());
        dto.setRegistrationDate(o.getRegistrationDate());
        return dto;
    }

    public static OrganDonor fromDto(OrganDonorRequestDto req) {
        OrganDonor o = new OrganDonor();
        o.setDonorName(req.getDonorName());
        o.setOrganType(req.getOrganType());
        o.setEmail(req.getEmail());
        o.setPhone(req.getPhone());
        o.setBloodGroup(req.getBloodGroup());
        o.setAge(req.getAge());
        o.setCity(req.getCity());
        o.setStatus("PENDING");
        return o;
    }
}
