package com.example.bloodplatform.controller;

import com.example.bloodplatform.dto.RecipientRequestDto;
import com.example.bloodplatform.dto.RecipientResponseDto;
import com.example.bloodplatform.mapper.RecipientMapper;
import com.example.bloodplatform.service.RecipientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/recipients")
public class RecipientController {
    private final RecipientService recipientService;

    @Autowired
    public RecipientController(RecipientService recipientService) {
        this.recipientService = recipientService;
    }

    @PostMapping
    public ResponseEntity<RecipientResponseDto> create(@Valid @RequestBody RecipientRequestDto req) {
        var recipient = recipientService.createRecipient(RecipientMapper.fromDto(req));
        return ResponseEntity.ok(RecipientMapper.toDto(recipient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipientResponseDto> get(@PathVariable UUID id) {
        var recipient = recipientService.getById(id);
        return ResponseEntity.ok(RecipientMapper.toDto(recipient));
    }

    @GetMapping
    public ResponseEntity<Page<RecipientResponseDto>> list(@RequestParam(defaultValue = "OPEN") String status,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        var recipients = recipientService.findByStatus(status, PageRequest.of(page, size)).map(RecipientMapper::toDto);
        return ResponseEntity.ok(recipients);
    }
}
