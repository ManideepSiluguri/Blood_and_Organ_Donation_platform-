package com.example.bloodplatform.controller;

import com.example.bloodplatform.dto.RecipientRequestDto;
import com.example.bloodplatform.dto.RecipientResponseDto;
import com.example.bloodplatform.mapper.RecipientMapper;
import com.example.bloodplatform.model.Recipient;
import com.example.bloodplatform.repository.RecipientRepository;
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
    private final RecipientRepository recipientRepository;

    @Autowired
    public RecipientController(RecipientRepository recipientRepository) {
        this.recipientRepository = recipientRepository;
    }

    @PostMapping
    public ResponseEntity<RecipientResponseDto> create(@Valid @RequestBody RecipientRequestDto req) {
        Recipient r = RecipientMapper.fromDto(req);
        Recipient saved = recipientRepository.save(r);
        return ResponseEntity.ok(RecipientMapper.toDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipientResponseDto> get(@PathVariable UUID id) {
        Recipient r = recipientRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(RecipientMapper.toDto(r));
    }

    @GetMapping
    public ResponseEntity<Page<RecipientResponseDto>> list(@RequestParam(defaultValue = "OPEN") String status,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        var recipients = recipientRepository.findByStatus(status, PageRequest.of(page, size)).map(RecipientMapper::toDto);
        return ResponseEntity.ok(recipients);
    }
}
