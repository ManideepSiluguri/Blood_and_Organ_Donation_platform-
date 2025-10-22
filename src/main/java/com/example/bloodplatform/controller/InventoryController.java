package com.example.bloodplatform.controller;

import com.example.bloodplatform.model.BloodInventory;
import com.example.bloodplatform.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) { this.inventoryService = inventoryService; }

    @PostMapping("/{bloodGroup}/add")
    public ResponseEntity<BloodInventory> addUnits(@PathVariable String bloodGroup, @RequestParam int units){
        return ResponseEntity.ok(inventoryService.addUnits(bloodGroup, units));
    }

    @PostMapping("/{bloodGroup}/consume")
    public ResponseEntity<BloodInventory> consume(@PathVariable String bloodGroup, @RequestParam int units){
        return ResponseEntity.ok(inventoryService.consumeUnits(bloodGroup, units));
    }

    @GetMapping("/{bloodGroup}")
    public ResponseEntity<BloodInventory> get(@PathVariable String bloodGroup){ return ResponseEntity.ok(inventoryService.getByBloodGroup(bloodGroup)); }
}
