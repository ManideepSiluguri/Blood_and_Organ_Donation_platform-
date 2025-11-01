package com.example.bloodplatform.mapper;

import com.example.bloodplatform.dto.OrganDonationRequestDto;
import com.example.bloodplatform.dto.OrganDonationResponseDto;
import com.example.bloodplatform.model.OrganDonation;

public class OrganDonationMapper {
    public static OrganDonationResponseDto toDto(OrganDonation o) {
        if (o == null) return null;
        OrganDonationResponseDto dto = new OrganDonationResponseDto();
        dto.setId(o.getId());
        dto.setOrganType(o.getOrganType());
        dto.setDonorName(o.getDonorName());
        dto.setRecipientName(o.getRecipientName());
        dto.setStatus(o.getStatus());
        dto.setCreatedAt(o.getCreatedAt());
        return dto;
    }

    public static OrganDonation fromDto(OrganDonationRequestDto req) {
        OrganDonation o = new OrganDonation();
        o.setOrganType(req.getOrganType());
        o.setDonorName(req.getDonorName());
        o.setRecipientName(req.getRecipientName());
        o.setStatus("PENDING");
        return o;
    }
}
