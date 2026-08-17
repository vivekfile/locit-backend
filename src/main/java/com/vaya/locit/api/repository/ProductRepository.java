package com.vaya.locit.api.repository;

import com.vaya.locit.api.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository
        extends JpaRepository<Product, Integer> {

    List<Product> findByBrandBrandId(int brandId);

    List<Product> findByCategoryCategoryId(int categoryId);

    List<Product> findByShopShopId(int shopId);

    List<Product> findByBrandBrandIdAndIsAvailableTrue(int brandId);

    List<Product> findByProductNameContainingIgnoreCase(String productName);
}