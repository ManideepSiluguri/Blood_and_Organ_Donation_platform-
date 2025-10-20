package com.example.bloodplatform.controller;

import com.example.bloodplatform.model.BloodType;
import com.example.bloodplatform.model.Donor;
import com.example.bloodplatform.service.DonorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/donors")
@RequiredArgsConstructor
public class DonorController {

    private final DonorService donorService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody Donor donor) {
        Donor saved = donorService.register(donor);
        return ResponseEntity.ok(Map.of(
                "message", "Donor registered successfully",
                "donorId", saved.getId(),
                "status", "SUCCESS"
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donor> get(@PathVariable Long id) {
        return donorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donor> update(@PathVariable Long id, @RequestBody Donor update) {
        try {
            return ResponseEntity.ok(donorService.update(id, update));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Donor>> search(@RequestParam BloodType bloodType,
                                              @RequestParam(required = false) String city) {
        return ResponseEntity.ok(donorService.searchByBloodAndCity(bloodType, city));
    }
}
