package com.example.bloodplatform.controller;

import com.example.bloodplatform.dto.AdminRequestDto;
import com.example.bloodplatform.dto.AdminResponseDto;
import com.example.bloodplatform.mapper.AdminMapper;
import com.example.bloodplatform.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<AdminResponseDto>> list() {
        var admins = adminService.findAll().stream()
                .map(AdminMapper::toDto)
                .toList();
        return ResponseEntity.ok(admins);
    }

    @PostMapping
    public ResponseEntity<AdminResponseDto> create(@Valid @RequestBody AdminRequestDto req) {
        var admin = adminService.createAdmin(AdminMapper.fromDto(req));
        return ResponseEntity.ok(AdminMapper.toDto(admin));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponseDto> get(@PathVariable UUID id) {
        var admin = adminService.getById(id);
        return ResponseEntity.ok(AdminMapper.toDto(admin));
    }
}
