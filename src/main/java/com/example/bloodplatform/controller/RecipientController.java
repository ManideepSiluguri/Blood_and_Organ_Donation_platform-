package com.example.bloodplatform.controller;

import com.example.bloodplatform.model.Recipient;
import com.example.bloodplatform.repository.RecipientRepository;
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
    public RecipientController(RecipientRepository recipientRepository) { this.recipientRepository = recipientRepository; }

    @PostMapping
    public ResponseEntity<Recipient> create(@RequestBody Recipient r){ return ResponseEntity.ok(recipientRepository.save(r)); }

    @GetMapping("/{id}")
    public ResponseEntity<Recipient> get(@PathVariable UUID id){ return ResponseEntity.ok(recipientRepository.findById(id).orElseThrow()); }

    @GetMapping
    public ResponseEntity<Page<Recipient>> list(@RequestParam(defaultValue="OPEN") String status, @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="10") int size){
        return ResponseEntity.ok(recipientRepository.findByStatus(status, PageRequest.of(page,size)));
    }
}
