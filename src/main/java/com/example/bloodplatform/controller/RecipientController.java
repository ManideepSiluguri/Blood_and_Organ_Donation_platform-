package com.example.bloodplatform.controller;

import com.example.bloodplatform.model.Recipient;
import com.example.bloodplatform.service.RecipientService;
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
    public RecipientController(RecipientService recipientService) { this.recipientService = recipientService; }

    @PostMapping
    public ResponseEntity<Recipient> create(@RequestBody Recipient r){ return ResponseEntity.ok(recipientService.create(r)); }

    @GetMapping("/{id}")
    public ResponseEntity<Recipient> get(@PathVariable UUID id){ return ResponseEntity.ok(recipientService.getById(id)); }

    @GetMapping
    public ResponseEntity<Page<Recipient>> list(@RequestParam(defaultValue="OPEN") String status, @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="10") int size){
        return ResponseEntity.ok(recipientService.listByStatus(status, PageRequest.of(page,size)));
    }
}