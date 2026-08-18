package com.vaya.locit.api.service;

import com.vaya.locit.api.entity.Inventory;
import com.vaya.locit.api.repository.InventoryRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(
            InventoryRepository inventoryRepository) {

        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(int id) {
        return inventoryRepository
                .findById(id)
                .orElse(null);
    }

    public Inventory getInventoryByProduct(int productId) {
        return inventoryRepository
                .findByProductProductId(productId)
                .orElse(null);
    }

    public Inventory createInventory(Inventory inventory) {

        int productId = inventory.getProduct().getProductId();

        if (inventoryRepository.existsByProductProductId(productId)) {
            throw new RuntimeException(
                    "Inventory already exists for this product"
            );
        }

        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(
            int id,
            Inventory inventory) {

        Inventory existingInventory =
                inventoryRepository.findById(id).orElse(null);

        if (existingInventory == null) {
            return null;
        }

        existingInventory.setProduct(inventory.getProduct());
        existingInventory.setAvailableStock(
                inventory.getAvailableStock()
        );

        return inventoryRepository.save(existingInventory);
    }

    public boolean deleteInventory(int id) {

        if (!inventoryRepository.existsById(id)) {
            return false;
        }

        inventoryRepository.deleteById(id);

        return true;
    }
}