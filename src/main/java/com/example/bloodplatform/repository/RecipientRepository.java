package com.example.bloodplatform.repository;

import com.example.bloodplatform.model.Recipient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecipientRepository extends JpaRepository<Recipient, UUID> {
    Page<Recipient> findByStatus(String status, Pageable pageable);
}
