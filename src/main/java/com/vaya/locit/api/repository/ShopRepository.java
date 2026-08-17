package com.vaya.locit.api.repository;

import com.vaya.locit.api.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    List<Shop> findBySellerUserId(int sellerId);

    List<Shop> findByCity(String city);

    List<Shop> findByState(String state);
}