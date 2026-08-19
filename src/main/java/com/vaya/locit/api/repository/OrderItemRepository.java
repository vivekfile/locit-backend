package com.vaya.locit.api.repository;

import com.vaya.locit.api.entity.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository
        extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrderOrderId(int orderId);

    List<OrderItem> findByProductProductId(int productId);
}