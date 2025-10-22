package com.example.bloodplatform.service.impl;

import com.example.bloodplatform.exception.ResourceNotFoundException;
import com.example.bloodplatform.model.BloodInventory;
import com.example.bloodplatform.repository.BloodInventoryRepository;
import com.example.bloodplatform.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final BloodInventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(BloodInventoryRepository inventoryRepository) { this.inventoryRepository = inventoryRepository; }

    @Override
    public BloodInventory addUnits(String bloodGroup, int units) {
        BloodInventory inv = inventoryRepository.findByBloodGroup(bloodGroup);
        if (inv == null) {
            inv = new BloodInventory();
            inv.setBloodGroup(bloodGroup);
            inv.setUnits(units);
        } else {
            inv.setUnits(inv.getUnits() + units);
        }
        inv.setUpdatedAt(LocalDateTime.now());
        return inventoryRepository.save(inv);
    }

    @Override
    public BloodInventory consumeUnits(String bloodGroup, int units) {
        BloodInventory inv = inventoryRepository.findByBloodGroup(bloodGroup);
        if (inv == null) throw new ResourceNotFoundException("Inventory for blood group: " + bloodGroup);
        int remaining = inv.getUnits() - units;
        if (remaining < 0) remaining = 0;
        inv.setUnits(remaining);
        inv.setUpdatedAt(LocalDateTime.now());
        return inventoryRepository.save(inv);
    }

    @Override
    public BloodInventory getByBloodGroup(String bloodGroup) { return inventoryRepository.findByBloodGroup(bloodGroup); }
}