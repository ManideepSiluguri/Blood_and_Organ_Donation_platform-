package com.example.bloodplatform.controller;

import com.example.bloodplatform.model.Admin;
import com.example.bloodplatform.repository.AdminRepository;
import com.example.bloodplatform.exception.ResourceNotFoundException;
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
    public AdminController(AdminRepository adminRepository) { this.adminRepository = adminRepository; }

    @GetMapping("/users")
    public ResponseEntity<List<Admin>> list(){ return ResponseEntity.ok(adminRepository.findAll()); }

    @PostMapping
    public ResponseEntity<Admin> create(@RequestBody Admin a){ return ResponseEntity.ok(adminRepository.save(a)); }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> get(@PathVariable UUID id){ return ResponseEntity.ok(adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin", id))); }
}