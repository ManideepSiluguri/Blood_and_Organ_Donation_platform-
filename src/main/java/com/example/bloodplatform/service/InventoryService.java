package com.example.bloodplatform.service;

import com.example.bloodplatform.model.BloodInventory;

public interface InventoryService {
    BloodInventory addUnits(String bloodGroup, int units);
    BloodInventory consumeUnits(String bloodGroup, int units);
    BloodInventory getByBloodGroup(String bloodGroup);
}
