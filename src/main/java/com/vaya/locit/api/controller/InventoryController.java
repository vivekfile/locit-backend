package com.vaya.locit.api.controller;

import com.vaya.locit.api.entity.Inventory;
import com.vaya.locit.api.service.InventoryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(
            InventoryService inventoryService) {

        this.inventoryService = inventoryService;
    }

    // GET /inventory
    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {

        List<Inventory> inventory =
                inventoryService.getAllInventory();

        return ResponseEntity.ok(inventory);
    }

    // GET /inventory/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(
            @PathVariable int id) {

        Inventory inventory =
                inventoryService.getInventoryById(id);

        if (inventory == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(inventory);
    }

    // GET /inventory/product/{productId}
    @GetMapping("/product/{productId}")
    public ResponseEntity<Inventory> getInventoryByProduct(
            @PathVariable int productId) {

        Inventory inventory =
                inventoryService.getInventoryByProduct(productId);

        if (inventory == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(inventory);
    }

    // POST /inventory
    @PostMapping
    public ResponseEntity<Inventory> createInventory(
            @RequestBody Inventory inventory) {

        Inventory createdInventory =
                inventoryService.createInventory(inventory);

        return ResponseEntity.ok(createdInventory);
    }

    // PUT /inventory/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(
            @PathVariable int id,
            @RequestBody Inventory inventory) {

        Inventory updatedInventory =
                inventoryService.updateInventory(
                        id,
                        inventory
                );

        if (updatedInventory == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedInventory);
    }

    // DELETE /inventory/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(
            @PathVariable int id) {

        boolean deleted =
                inventoryService.deleteInventory(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}