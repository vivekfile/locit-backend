package com.vaya.locit.api.repository;

import com.vaya.locit.api.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<Order, Integer> {

    List<Order> findByBuyerUserId(int buyerId);

    List<Order> findByShopShopId(int shopId);

    List<Order> findByOrderStatus(Order.OrderStatus orderStatus);

    List<Order> findByBuyerUserIdOrderByOrderedAtDesc(
            int buyerId
    );
}