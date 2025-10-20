package com.example.bloodplatform.controller;

import com.example.bloodplatform.model.EmergencyRequest;
import com.example.bloodplatform.service.EmergencyRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/emergency")
@RequiredArgsConstructor
public class EmergencyController {

    private final EmergencyRequestService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EmergencyRequest request) {
        EmergencyRequest saved = service.create(request);
        return ResponseEntity.ok(Map.of(
                "message", "Emergency request created successfully",
                "requestId", saved.getId(),
                "status", "SUCCESS"
        ));
    }

    @GetMapping("/open")
    public ResponseEntity<List<EmergencyRequest>> openRequests() {
        return ResponseEntity.ok(service.openRequests());
    }

    @PostMapping("/{id}/resolve")
    public ResponseEntity<Void> resolve(@PathVariable Long id) {
        service.markResolved(id);
        return ResponseEntity.ok().build();
    }
}
