package com.example.bloodplatform.controller;

import com.example.bloodplatform.dto.OrganDonationRequestDto;
import com.example.bloodplatform.dto.OrganDonationResponseDto;
import com.example.bloodplatform.mapper.OrganDonationMapper;
import com.example.bloodplatform.model.OrganDonation;
import com.example.bloodplatform.service.OrganDonationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/organs")
public class OrganDonationController {
    private final OrganDonationService organService;

    @Autowired
    public OrganDonationController(OrganDonationService organService) {
        this.organService = organService;
    }

    @PostMapping
    public ResponseEntity<OrganDonationResponseDto> create(@Valid @RequestBody OrganDonationRequestDto req) {
        var organ = organService.createOrganDonation(OrganDonationMapper.fromDto(req));
        return ResponseEntity.ok(OrganDonationMapper.toDto(organ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganDonationResponseDto> get(@PathVariable UUID id) {
        var organ = organService.getById(id);
        return ResponseEntity.ok(OrganDonationMapper.toDto(organ));
    }

    @GetMapping
    public ResponseEntity<List<OrganDonationResponseDto>> list(@RequestParam(required = false) String organType) {
        List<OrganDonation> organs = (organType != null && !organType.isBlank())
                ? organService.findByOrganType(organType)
                : organService.findAll();
        List<OrganDonationResponseDto> result = organs.stream()
                .map(OrganDonationMapper::toDto)
                .toList();
        return ResponseEntity.ok(result);
    }
}
