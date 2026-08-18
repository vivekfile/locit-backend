package com.vaya.locit.api.repository;

import com.vaya.locit.api.entity.CartItem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository
        extends JpaRepository<CartItem, Integer> {

    List<CartItem> findByUserUserId(int userId);

    Optional<CartItem> findByUserUserIdAndProductProductId(
            int userId,
            int productId
    );

    boolean existsByUserUserIdAndProductProductId(
            int userId,
            int productId
    );
}