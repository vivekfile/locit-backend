package com.vaya.locit.api.repository;

import com.vaya.locit.api.entity.Review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository
        extends JpaRepository<Review, Integer> {

    List<Review> findByProductProductId(int productId);

    List<Review> findByUserUserId(int userId);

    List<Review> findByProductProductIdOrderByCreatedAtDesc(
            int productId
    );
}