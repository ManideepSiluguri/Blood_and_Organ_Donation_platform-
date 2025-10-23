package com.example.bloodplatform.repository;

import com.example.bloodplatform.model.BloodInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BloodInventoryRepository extends JpaRepository<BloodInventory, UUID> {
    BloodInventory findByBloodGroup(String bloodGroup);
}
