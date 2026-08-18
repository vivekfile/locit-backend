package com.vaya.locit.api.repository;

import com.vaya.locit.api.entity.Wishlist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository
        extends JpaRepository<Wishlist, Integer> {

    List<Wishlist> findByUserUserId(int userId);

    Optional<Wishlist> findByUserUserIdAndProductProductId(
            int userId,
            int productId
    );

    boolean existsByUserUserIdAndProductProductId(
            int userId,
            int productId
    );
}
