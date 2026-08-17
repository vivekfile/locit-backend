package com.vaya.locit.api.repository;

import com.vaya.locit.api.entity.ProductImage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository
        extends JpaRepository<ProductImage, Integer> {

    List<ProductImage> findByProductProductId(int productId);
}