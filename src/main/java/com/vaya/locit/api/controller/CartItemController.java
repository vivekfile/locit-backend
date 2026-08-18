package com.vaya.locit.api.controller;

import com.vaya.locit.api.entity.CartItem;
import com.vaya.locit.api.service.CartItemService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(
            CartItemService cartItemService) {

        this.cartItemService = cartItemService;
    }

    // GET /cart-items
    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {

        List<CartItem> cartItems =
                cartItemService.getAllCartItems();

        return ResponseEntity.ok(cartItems);
    }

    // GET /cart-items/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(
            @PathVariable int id) {

        CartItem cartItem =
                cartItemService.getCartItemById(id);

        if (cartItem == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cartItem);
    }

    // GET /cart-items/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartItem>> getCartByUser(
            @PathVariable int userId) {

        List<CartItem> cartItems =
                cartItemService.getCartByUser(userId);

        return ResponseEntity.ok(cartItems);
    }

    // POST /cart-items
    @PostMapping
    public ResponseEntity<CartItem> addToCart(
            @RequestBody CartItem cartItem) {

        CartItem createdItem =
                cartItemService.addToCart(cartItem);

        return ResponseEntity.ok(createdItem);
    }

    // PUT /cart-items/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(
            @PathVariable int id,
            @RequestBody CartItem cartItem) {

        CartItem updatedItem =
                cartItemService.updateCartItem(
                        id,
                        cartItem
                );

        if (updatedItem == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedItem);
    }

    // DELETE /cart-items/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(
            @PathVariable int id) {

        boolean deleted =
                cartItemService.deleteCartItem(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // DELETE /cart-items/user/{userId}
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> clearUserCart(
            @PathVariable int userId) {

        cartItemService.clearUserCart(userId);

        return ResponseEntity.noContent().build();
    }
}