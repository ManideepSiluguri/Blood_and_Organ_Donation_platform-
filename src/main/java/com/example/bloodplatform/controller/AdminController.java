package com.example.bloodplatform.controller;

import com.example.bloodplatform.dto.AdminRequestDto;
import com.example.bloodplatform.dto.AdminResponseDto;
import com.example.bloodplatform.mapper.AdminMapper;
import com.example.bloodplatform.model.Admin;
import com.example.bloodplatform.repository.AdminRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("/users")
    public ResponseEntity<List<AdminResponseDto>> list() {
        var admins = adminRepository.findAll().stream()
                .map(AdminMapper::toDto)
                .toList();
        return ResponseEntity.ok(admins);
    }

    @PostMapping
    public ResponseEntity<AdminResponseDto> create(@Valid @RequestBody AdminRequestDto req) {
        Admin a = AdminMapper.fromDto(req);
        Admin saved = adminRepository.save(a);
        return ResponseEntity.ok(AdminMapper.toDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponseDto> get(@PathVariable UUID id) {
        Admin a = adminRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(AdminMapper.toDto(a));
    }
}
