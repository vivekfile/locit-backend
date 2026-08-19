package com.vaya.locit.api.controller;

import com.vaya.locit.api.entity.Order;
import com.vaya.locit.api.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            OrderService orderService) {

        this.orderService = orderService;
    }

    // GET /orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {

        List<Order> orders =
                orderService.getAllOrders();

        return ResponseEntity.ok(orders);
    }

    // GET /orders/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(
            @PathVariable int id) {

        Order order =
                orderService.getOrderById(id);

        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(order);
    }

    // GET /orders/buyer/{buyerId}
    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<List<Order>> getOrdersByBuyer(
            @PathVariable int buyerId) {

        List<Order> orders =
                orderService.getOrdersByBuyer(
                        buyerId
                );

        return ResponseEntity.ok(orders);
    }

    // GET /orders/shop/{shopId}
    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<Order>> getOrdersByShop(
            @PathVariable int shopId) {

        List<Order> orders =
                orderService.getOrdersByShop(
                        shopId
                );

        return ResponseEntity.ok(orders);
    }

    // GET /orders/status/{status}
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(
            @PathVariable Order.OrderStatus status) {

        List<Order> orders =
                orderService.getOrdersByStatus(status);

        return ResponseEntity.ok(orders);
    }

    // POST /orders
    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody Order order) {

        Order createdOrder =
                orderService.createOrder(order);

        return ResponseEntity.ok(createdOrder);
    }

    // PATCH /orders/{id}/status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable int id,
            @RequestParam Order.OrderStatus status) {

        Order updatedOrder =
                orderService.updateOrderStatus(
                        id,
                        status
                );

        if (updatedOrder == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedOrder);
    }

    // DELETE /orders/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(
            @PathVariable int id) {

        boolean deleted =
                orderService.deleteOrder(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}