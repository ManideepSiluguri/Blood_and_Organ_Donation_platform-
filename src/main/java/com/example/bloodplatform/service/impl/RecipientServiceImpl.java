package com.example.bloodplatform.service.impl;

import com.example.bloodplatform.exception.ResourceNotFoundException;
import com.example.bloodplatform.model.Recipient;
import com.example.bloodplatform.repository.RecipientRepository;
import com.example.bloodplatform.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class RecipientServiceImpl implements RecipientService {
    private final RecipientRepository recipientRepository;

    @Autowired
    public RecipientServiceImpl(RecipientRepository recipientRepository) {
        this.recipientRepository = recipientRepository;
    }

    @Override
    public Recipient createRecipient(Recipient recipient) {
        return recipientRepository.save(recipient);
    }

    @Override
    public Recipient getById(UUID id) {
        return recipientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipient", id));
    }

    @Override
    public Page<Recipient> findByStatus(String status, Pageable pageable) {
        return recipientRepository.findByStatus(status, pageable);
    }
}
