package com.example.bloodplatform.controller;

import com.example.bloodplatform.model.Donor;
import com.example.bloodplatform.service.DonorService;
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
    public ResponseEntity<Donor> create(@RequestBody Donor donor) {
        Donor saved = donorService.createDonor(donor);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donor> getById(@PathVariable UUID id) { return ResponseEntity.ok(donorService.getById(id)); }

    @GetMapping
    public ResponseEntity<Page<Donor>> list(@RequestParam(required=false) String city,
                                            @RequestParam(defaultValue="0") int page,
                                            @RequestParam(defaultValue="10") int size) {
        PageRequest pr = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstName"));
        return ResponseEntity.ok(donorService.searchDonors(city, pr));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donor> update(@PathVariable UUID id, @RequestBody Donor donor) { return ResponseEntity.ok(donorService.updateDonor(id, donor)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) { donorService.deleteDonor(id); return ResponseEntity.noContent().build(); }

    @GetMapping("/search-by-blood")
    public ResponseEntity<List<Donor>> byBlood(@RequestParam String bloodGroup) { return ResponseEntity.ok(donorService.findByBloodGroup(bloodGroup)); }
}