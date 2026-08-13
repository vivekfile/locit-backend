package com.vaya.locit.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaya.locit.api.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    List<Brand> findByCategoryCategoryId(int categoryId);
}
