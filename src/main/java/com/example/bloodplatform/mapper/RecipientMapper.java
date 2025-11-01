package com.example.bloodplatform.mapper;

import com.example.bloodplatform.dto.RecipientRequestDto;
import com.example.bloodplatform.dto.RecipientResponseDto;
import com.example.bloodplatform.model.Recipient;

public class RecipientMapper {
    public static RecipientResponseDto toDto(Recipient r) {
        if (r == null) return null;
        RecipientResponseDto dto = new RecipientResponseDto();
        dto.setId(r.getId());
        dto.setName(r.getName());
        dto.setEmail(r.getEmail());
        dto.setPhone(r.getPhone());
        dto.setBloodGroupNeeded(r.getBloodGroupNeeded());
        dto.setHospital(r.getHospital());
        dto.setCity(r.getCity());
        dto.setRequestDate(r.getRequestDate());
        dto.setStatus(r.getStatus());
        return dto;
    }

    public static Recipient fromDto(RecipientRequestDto req) {
        Recipient r = new Recipient();
        r.setName(req.getName());
        r.setEmail(req.getEmail());
        r.setPhone(req.getPhone());
        r.setBloodGroupNeeded(req.getBloodGroupNeeded());
        r.setHospital(req.getHospital());
        r.setCity(req.getCity());
        return r;
    }
}
