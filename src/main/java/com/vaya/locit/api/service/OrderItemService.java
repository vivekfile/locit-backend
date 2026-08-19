package com.vaya.locit.api.service;

import com.vaya.locit.api.entity.OrderItem;
import com.vaya.locit.api.entity.Product;
import com.vaya.locit.api.repository.OrderItemRepository;
import com.vaya.locit.api.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public OrderItemService(
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository) {

        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemById(int id) {

        return orderItemRepository
                .findById(id)
                .orElse(null);
    }

    public List<OrderItem> getItemsByOrder(int orderId) {

        return orderItemRepository
                .findByOrderOrderId(orderId);
    }

    public List<OrderItem> getItemsByProduct(int productId) {

        return orderItemRepository
                .findByProductProductId(productId);
    }

    public OrderItem createOrderItem(OrderItem orderItem) {

        if (orderItem.getQuantity() == null ||
                orderItem.getQuantity() <= 0) {

            throw new RuntimeException(
                    "Quantity must be greater than 0"
            );
        }

        int productId =
                orderItem.getProduct().getProductId();

        Product product =
                productRepository
                        .findById(productId)
                        .orElse(null);

        if (product == null) {
            throw new RuntimeException(
                    "Product not found"
            );
        }

        if (Boolean.FALSE.equals(
                product.getIsAvailable())) {

            throw new RuntimeException(
                    "Product is not available"
            );
        }

        /*
         * Take the CURRENT product price and store
         * it permanently in the order item.
         */
        orderItem.setProduct(product);

        orderItem.setPrice(
                product.getPrice()
        );

        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(
            int id,
            OrderItem orderItem) {

        OrderItem existingItem =
                orderItemRepository
                        .findById(id)
                        .orElse(null);

        if (existingItem == null) {
            return null;
        }

        if (orderItem.getQuantity() == null ||
                orderItem.getQuantity() <= 0) {

            throw new RuntimeException(
                    "Quantity must be greater than 0"
            );
        }

        existingItem.setQuantity(
                orderItem.getQuantity()
        );

        /*
         * Do NOT update price here.
         *
         * The price represents the price at the
         * time the order was created.
         */

        return orderItemRepository.save(
                existingItem
        );
    }

    public boolean deleteOrderItem(int id) {

        if (!orderItemRepository.existsById(id)) {
            return false;
        }

        orderItemRepository.deleteById(id);

        return true;
    }
}