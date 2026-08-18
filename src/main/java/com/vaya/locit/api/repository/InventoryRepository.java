package com.vaya.locit.api.repository;

import com.vaya.locit.api.entity.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository
        extends JpaRepository<Inventory, Integer> {

    Optional<Inventory> findByProductProductId(int productId);

    boolean existsByProductProductId(int productId);
}
