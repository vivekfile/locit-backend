package com.vaya.locit.api.service;

import com.vaya.locit.api.entity.CartItem;
import com.vaya.locit.api.entity.Product;
import com.vaya.locit.api.repository.CartItemRepository;
import com.vaya.locit.api.repository.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartItemService(
            CartItemRepository cartItemRepository,
            ProductRepository productRepository) {

        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemById(int id) {
        return cartItemRepository
                .findById(id)
                .orElse(null);
    }

    public List<CartItem> getCartByUser(int userId) {
        return cartItemRepository
                .findByUserUserId(userId);
    }

    @Transactional
    public CartItem addToCart(CartItem cartItem) {

        if (cartItem.getQuantity() == null ||
                cartItem.getQuantity() <= 0) {

            throw new RuntimeException(
                    "Quantity must be greater than 0"
            );
        }

        int userId =
                cartItem.getUser().getUserId();

        int productId =
                cartItem.getProduct().getProductId();

        Product product =
                productRepository
                        .findById(productId)
                        .orElse(null);

        if (product == null) {
            throw new RuntimeException(
                    "Product not found"
            );
        }

        if (Boolean.FALSE.equals(product.getIsAvailable())) {
            throw new RuntimeException(
                    "Product is not available"
            );
        }

        /*
         * Check whether this product is already
         * present in this user's cart.
         */
        CartItem existingItem =
                cartItemRepository
                        .findByUserUserIdAndProductProductId(
                                userId,
                                productId
                        )
                        .orElse(null);

        if (existingItem != null) {

            int newQuantity =
                    existingItem.getQuantity()
                            + cartItem.getQuantity();

            existingItem.setQuantity(newQuantity);

            return cartItemRepository.save(existingItem);
        }

        /*
         * Attach the actual Product object
         * from the database.
         */
        cartItem.setProduct(product);

        return cartItemRepository.save(cartItem);
    }

    @Transactional
    public CartItem updateCartItem(
            int id,
            CartItem cartItem) {

        CartItem existingItem =
                cartItemRepository
                        .findById(id)
                        .orElse(null);

        if (existingItem == null) {
            return null;
        }

        if (cartItem.getQuantity() == null ||
                cartItem.getQuantity() <= 0) {

            throw new RuntimeException(
                    "Quantity must be greater than 0"
            );
        }

        Product product =
                existingItem.getProduct();

        if (product == null) {
            throw new RuntimeException(
                    "Product not found"
            );
        }

        if (Boolean.FALSE.equals(product.getIsAvailable())) {
            throw new RuntimeException(
                    "Product is not available"
            );
        }

        existingItem.setQuantity(
                cartItem.getQuantity()
        );

        return cartItemRepository.save(existingItem);
    }

    public boolean deleteCartItem(int id) {

        if (!cartItemRepository.existsById(id)) {
            return false;
        }

        cartItemRepository.deleteById(id);

        return true;
    }

    public void clearUserCart(int userId) {

        List<CartItem> cartItems =
                cartItemRepository
                        .findByUserUserId(userId);

        cartItemRepository.deleteAll(cartItems);
    }
}