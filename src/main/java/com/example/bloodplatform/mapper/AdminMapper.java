package com.example.bloodplatform.mapper;

import com.example.bloodplatform.dto.AdminRequestDto;
import com.example.bloodplatform.dto.AdminResponseDto;
import com.example.bloodplatform.model.Admin;

public class AdminMapper {
    public static AdminResponseDto toDto(Admin a) {
        if (a == null) return null;
        AdminResponseDto dto = new AdminResponseDto();
        dto.setId(a.getId());
        dto.setUsername(a.getUsername());
        dto.setEmail(a.getEmail());
        dto.setRole(a.getRole());
        return dto;
    }

    public static Admin fromDto(AdminRequestDto req) {
        Admin a = new Admin();
        a.setUsername(req.getUsername());
        a.setEmail(req.getEmail());
        a.setRole(req.getRole());
        return a;
    }
}
