package com.example.bloodplatform.controller;

import com.example.bloodplatform.repository.DonorRepository;
import com.example.bloodplatform.repository.EmergencyRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final DonorRepository donorRepo;
    private final EmergencyRequestRepository requestRepo;

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> stats() {
        long donors = donorRepo.count();
        long openRequests = requestRepo.findUnresolvedRequests().size();
        return ResponseEntity.ok(Map.of(
                "totalDonors", donors,
                "openRequests", openRequests
        ));
    }
}
