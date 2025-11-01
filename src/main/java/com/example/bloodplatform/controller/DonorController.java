package com.example.bloodplatform.controller;

import com.example.bloodplatform.dto.DonorRequestDto;
import com.example.bloodplatform.dto.DonorResponseDto;
import com.example.bloodplatform.mapper.DonorMapper;
import com.example.bloodplatform.service.DonorService;
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

@RestController
@RequestMapping("/api/donors")
public class DonorController {
    private final DonorService donorService;
    @Autowired
    public DonorController(DonorService donorService) { this.donorService = donorService; }

    @PostMapping
    public ResponseEntity<DonorResponseDto> create(@Valid @RequestBody DonorRequestDto req) {
        var donor = donorService.createDonor(DonorMapper.fromDto(req));
        return ResponseEntity.status(HttpStatus.CREATED).body(DonorMapper.toDto(donor));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DonorResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(DonorMapper.toDto(donorService.getById(id)));
    }
    @GetMapping
    public ResponseEntity<Page<DonorResponseDto>> list(@RequestParam(required = false) String city,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        PageRequest pr = PageRequest.of(page, size, Sort.by("firstName"));
        var donors = donorService.searchDonors(city, pr).map(DonorMapper::toDto);
        return ResponseEntity.ok(donors);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DonorResponseDto> update(@PathVariable UUID id, @Valid @RequestBody DonorRequestDto req) {
        var donor = donorService.updateDonor(id, DonorMapper.fromDto(req));
        return ResponseEntity.ok(DonorMapper.toDto(donor));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        donorService.deleteDonor(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search-by-blood")
    public ResponseEntity<List<DonorResponseDto>> byBlood(@RequestParam String bloodGroup) {
        var donors = donorService.findByBloodGroup(bloodGroup).stream().map(DonorMapper::toDto).toList();
        return ResponseEntity.ok(donors);
    }
}
