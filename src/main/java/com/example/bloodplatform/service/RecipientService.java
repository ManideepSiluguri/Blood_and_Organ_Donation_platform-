package com.example.bloodplatform.service;

import com.example.bloodplatform.model.Recipient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RecipientService {
    Recipient create(Recipient recipient);
    Recipient getById(UUID id);
    Page<Recipient> listByStatus(String status, Pageable pageable);
}