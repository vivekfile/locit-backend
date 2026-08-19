package com.vaya.locit.api.repository;

import com.vaya.locit.api.entity.Bill;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository
        extends JpaRepository<Bill, Integer> {

    List<Bill> findByBuyerUserId(int buyerId);

    List<Bill> findByShopShopId(int shopId);
}