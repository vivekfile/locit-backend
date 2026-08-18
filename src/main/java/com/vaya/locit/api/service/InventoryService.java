package com.vaya.locit.api.service;

import com.vaya.locit.api.entity.Inventory;
import com.vaya.locit.api.entity.Product;
import com.vaya.locit.api.repository.InventoryRepository;
import com.vaya.locit.api.repository.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public InventoryService(
            InventoryRepository inventoryRepository,
            ProductRepository productRepository) {

        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
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

    @Transactional
    public Inventory createInventory(Inventory inventory) {

        int productId =
                inventory.getProduct().getProductId();

        if (inventoryRepository
                .existsByProductProductId(productId)) {

            throw new RuntimeException(
                    "Inventory already exists for this product"
            );
        }

        // Find the actual product from database
        Product product =
                productRepository
                        .findById(productId)
                        .orElse(null);

        if (product == null) {
            throw new RuntimeException(
                    "Product not found"
            );
        }

        // Set the same stock in Product
        product.setStock(
                inventory.getAvailableStock()
        );

        // Save Product
        productRepository.save(product);

        // Attach actual Product object
        inventory.setProduct(product);

        // Save Inventory
        return inventoryRepository.save(inventory);
    }

    @Transactional
    public Inventory updateInventory(
            int id,
            Inventory inventory) {

        Inventory existingInventory =
                inventoryRepository
                        .findById(id)
                        .orElse(null);

        if (existingInventory == null) {
            return null;
        }

        // Get existing product
        Product product =
                existingInventory.getProduct();

        if (product == null) {
            throw new RuntimeException(
                    "Product not found for inventory"
            );
        }

        /*
         * Update BOTH values
         */

        Integer newStock =
                inventory.getAvailableStock();

        // Inventory
        existingInventory.setAvailableStock(
                newStock
        );

        // Product
        product.setStock(
                newStock
        );

        // Save Product
        productRepository.save(product);

        // Save Inventory
        return inventoryRepository.save(
                existingInventory
        );
    }

    @Transactional
    public boolean deleteInventory(int id) {

        Inventory inventory =
                inventoryRepository
                        .findById(id)
                        .orElse(null);

        if (inventory == null) {
            return false;
        }

        /*
         * When inventory is deleted,
         * set product stock to 0.
         */
        Product product =
                inventory.getProduct();

        if (product != null) {

            product.setStock(0);

            productRepository.save(product);
        }

        inventoryRepository.delete(inventory);

        return true;
    }
}