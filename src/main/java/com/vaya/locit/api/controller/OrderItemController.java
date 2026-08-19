package com.vaya.locit.api.controller;

import com.vaya.locit.api.entity.OrderItem;
import com.vaya.locit.api.service.OrderItemService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(
            OrderItemService orderItemService) {

        this.orderItemService = orderItemService;
    }

    // GET /order-items
    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {

        List<OrderItem> items =
                orderItemService.getAllOrderItems();

        return ResponseEntity.ok(items);
    }

    // GET /order-items/{id}
    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(
            @PathVariable int id) {

        OrderItem item =
                orderItemService.getOrderItemById(id);

        if (item == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(item);
    }

    // GET /order-items/order/{orderId}
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItem>> getItemsByOrder(
            @PathVariable int orderId) {

        List<OrderItem> items =
                orderItemService.getItemsByOrder(
                        orderId
                );

        return ResponseEntity.ok(items);
    }

    // GET /order-items/product/{productId}
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<OrderItem>> getItemsByProduct(
            @PathVariable int productId) {

        List<OrderItem> items =
                orderItemService.getItemsByProduct(
                        productId
                );

        return ResponseEntity.ok(items);
    }

    // POST /order-items
    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(
            @RequestBody OrderItem orderItem) {

        OrderItem createdItem =
                orderItemService.createOrderItem(
                        orderItem
                );

        return ResponseEntity.ok(createdItem);
    }

    // PUT /order-items/{id}
    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(
            @PathVariable int id,
            @RequestBody OrderItem orderItem) {

        OrderItem updatedItem =
                orderItemService.updateOrderItem(
                        id,
                        orderItem
                );

        if (updatedItem == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedItem);
    }

    // DELETE /order-items/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(
            @PathVariable int id) {

        boolean deleted =
                orderItemService.deleteOrderItem(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
