package com.example.bloodplatform.controller;

import com.example.bloodplatform.dto.OrganDonationRequestDto;
import com.example.bloodplatform.dto.OrganDonationResponseDto;
import com.example.bloodplatform.mapper.OrganDonationMapper;
import com.example.bloodplatform.model.OrganDonation;
import com.example.bloodplatform.repository.OrganDonationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/organs")
public class OrganDonationController {
    private final OrganDonationRepository organDonationRepository;

    @Autowired
    public OrganDonationController(OrganDonationRepository organDonationRepository) {
        this.organDonationRepository = organDonationRepository;
    }

    @PostMapping
    public ResponseEntity<OrganDonationResponseDto> create(@Valid @RequestBody OrganDonationRequestDto req) {
        OrganDonation o = OrganDonationMapper.fromDto(req);
        OrganDonation saved = organDonationRepository.save(o);
        return ResponseEntity.ok(OrganDonationMapper.toDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganDonationResponseDto> get(@PathVariable UUID id) {
        OrganDonation o = organDonationRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(OrganDonationMapper.toDto(o));
    }

    @GetMapping
    public ResponseEntity<List<OrganDonationResponseDto>> list(@RequestParam(required = false) String organType) {
        List<OrganDonation> organs = (organType != null && !organType.isBlank())
                ? organDonationRepository.findByOrganType(organType)
                : organDonationRepository.findAll();
        List<OrganDonationResponseDto> result = organs.stream()
                .map(OrganDonationMapper::toDto)
                .toList();
        return ResponseEntity.ok(result);
    }
}
