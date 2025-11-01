package com.example.bloodplatform.controller;

import com.example.bloodplatform.dto.BloodDonorRequestDto;
import com.example.bloodplatform.dto.BloodDonorResponseDto;
import com.example.bloodplatform.mapper.BloodDonorMapper;
import com.example.bloodplatform.service.BloodDonorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/api/blood-donors")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class BloodDonorController {
    private final BloodDonorService donorService;

    @Autowired
    public BloodDonorController(BloodDonorService donorService) {
        this.donorService = donorService;
    }

    @PostMapping
    public ResponseEntity<BloodDonorResponseDto> create(@Valid @RequestBody BloodDonorRequestDto req) {
        var donor = donorService.createDonor(BloodDonorMapper.fromDto(req));
        return ResponseEntity.status(HttpStatus.CREATED).body(BloodDonorMapper.toDto(donor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodDonorResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(BloodDonorMapper.toDto(donorService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<BloodDonorResponseDto>> list(@RequestParam(required = false) String city,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        PageRequest pr = PageRequest.of(page, size, Sort.by("firstName"));
        var donors = donorService.searchDonors(city, pr).map(BloodDonorMapper::toDto);
        return ResponseEntity.ok(donors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloodDonorResponseDto> update(@PathVariable UUID id, @Valid @RequestBody BloodDonorRequestDto req) {
        var donor = donorService.updateDonor(id, BloodDonorMapper.fromDto(req));
        return ResponseEntity.ok(BloodDonorMapper.toDto(donor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        donorService.deleteDonor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search-by-blood")
    public ResponseEntity<List<BloodDonorResponseDto>> byBlood(@RequestParam String bloodGroup) {
        var donors = donorService.findByBloodGroup(bloodGroup).stream().map(BloodDonorMapper::toDto).toList();
        return ResponseEntity.ok(donors);
    }
}
