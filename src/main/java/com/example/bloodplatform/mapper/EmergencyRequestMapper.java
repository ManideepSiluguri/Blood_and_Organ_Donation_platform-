package com.example.bloodplatform.mapper;

import com.example.bloodplatform.dto.EmergencyRequestDto;
import com.example.bloodplatform.dto.EmergencyResponseDto;
import com.example.bloodplatform.model.EmergencyRequest;

public class EmergencyRequestMapper {
    public static EmergencyResponseDto toDto(EmergencyRequest e) {
        if (e == null) return null;
        EmergencyResponseDto dto = new EmergencyResponseDto();
        dto.setId(e.getId());
        dto.setDescription(e.getDescription());
        dto.setLocation(e.getLocation());
        dto.setContactPhone(e.getContactPhone());
        dto.setBloodGroupNeeded(e.getBloodGroupNeeded());
        dto.setCreatedAt(e.getCreatedAt());
        dto.setStatus(e.getStatus());
        dto.setResponder(e.getResponder());
        return dto;
    }

    public static EmergencyRequest fromDto(EmergencyRequestDto req) {
        EmergencyRequest e = new EmergencyRequest();
        e.setDescription(req.getDescription());
        e.setLocation(req.getLocation());
        e.setContactPhone(req.getContactPhone());
        e.setBloodGroupNeeded(req.getBloodGroupNeeded());
        e.setStatus("NEW");
        return e;
    }
}
