package com.example.bloodplatform.controller;

import com.example.bloodplatform.dto.OrganDonorRequestDto;
import com.example.bloodplatform.dto.OrganDonorResponseDto;
import com.example.bloodplatform.mapper.OrganDonorMapper;
import com.example.bloodplatform.model.OrganDonor;
import com.example.bloodplatform.service.OrganDonorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/api/organ-donors")@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})

public class OrganDonorController {
    private final OrganDonorService organService;

    @Autowired
    public OrganDonorController(OrganDonorService organService) {
        this.organService = organService;
    }

    @PostMapping
    public ResponseEntity<OrganDonorResponseDto> create(@Valid @RequestBody OrganDonorRequestDto req) {
        var organ = organService.createOrganDonor(OrganDonorMapper.fromDto(req));
        return ResponseEntity.ok(OrganDonorMapper.toDto(organ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganDonorResponseDto> get(@PathVariable UUID id) {
        var organ = organService.getById(id);
        return ResponseEntity.ok(OrganDonorMapper.toDto(organ));
    }

    @GetMapping
    public ResponseEntity<List<OrganDonorResponseDto>> list(
            @RequestParam(required = false) String organType,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String status
    ) {
        List<OrganDonor> organs;
        if (organType != null) {
            organs = organService.findByOrganType(organType);
        } else if (city != null) {
            organs = organService.findByCity(city);
        } else if (status != null) {
            organs = organService.findByStatus(status);
        } else {
            organs = organService.findAll();
        }
        var result = organs.stream().map(OrganDonorMapper::toDto).toList();
        return ResponseEntity.ok(result);
    }
}
