package com.vaya.locit.api.service;

import com.vaya.locit.api.entity.Order;
import com.vaya.locit.api.repository.OrderRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(
            OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int id) {

        return orderRepository
                .findById(id)
                .orElse(null);
    }

    public List<Order> getOrdersByBuyer(int buyerId) {

        return orderRepository
                .findByBuyerUserIdOrderByOrderedAtDesc(
                        buyerId
                );
    }

    public List<Order> getOrdersByShop(int shopId) {

        return orderRepository
                .findByShopShopId(shopId);
    }

    public List<Order> getOrdersByStatus(
            Order.OrderStatus status) {

        return orderRepository
                .findByOrderStatus(status);
    }

    public Order createOrder(Order order) {

        if (order.getTotalAmount() == null ||
                order.getTotalAmount() < 0) {

            throw new RuntimeException(
                    "Total amount cannot be negative"
            );
        }

        if (order.getOrderStatus() == null) {
            order.setOrderStatus(
                    Order.OrderStatus.Pending
            );
        }

        return orderRepository.save(order);
    }

    public Order updateOrderStatus(
            int id,
            Order.OrderStatus status) {

        Order existingOrder =
                orderRepository
                        .findById(id)
                        .orElse(null);

        if (existingOrder == null) {
            return null;
        }

        existingOrder.setOrderStatus(status);

        return orderRepository.save(existingOrder);
    }

    public boolean deleteOrder(int id) {

        if (!orderRepository.existsById(id)) {
            return false;
        }

        orderRepository.deleteById(id);

        return true;
    }
}