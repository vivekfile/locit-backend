package com.vaya.locit.api.controller;

import com.vaya.locit.api.entity.Wishlist;
import com.vaya.locit.api.service.WishlistService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(
            WishlistService wishlistService) {

        this.wishlistService = wishlistService;
    }

    // GET /wishlist
    @GetMapping
    public ResponseEntity<List<Wishlist>> getAllWishlistItems() {

        List<Wishlist> wishlist =
                wishlistService.getAllWishlistItems();

        return ResponseEntity.ok(wishlist);
    }

    // GET /wishlist/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> getWishlistById(
            @PathVariable int id) {

        Wishlist wishlist =
                wishlistService.getWishlistById(id);

        if (wishlist == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(wishlist);
    }

    // GET /wishlist/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Wishlist>> getWishlistByUser(
            @PathVariable int userId) {

        List<Wishlist> wishlist =
                wishlistService.getWishlistByUser(userId);

        return ResponseEntity.ok(wishlist);
    }

    // POST /wishlist
    @PostMapping
    public ResponseEntity<Wishlist> addToWishlist(
            @RequestBody Wishlist wishlist) {

        Wishlist createdWishlist =
                wishlistService.addToWishlist(wishlist);

        return ResponseEntity.ok(createdWishlist);
    }

    // DELETE /wishlist/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFromWishlist(
            @PathVariable int id) {

        boolean deleted =
                wishlistService.removeFromWishlist(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // DELETE /wishlist/user/{userId}/product/{productId}
    @DeleteMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<Void> removeProductFromWishlist(
            @PathVariable int userId,
            @PathVariable int productId) {

        boolean deleted =
                wishlistService.removeProductFromWishlist(
                        userId,
                        productId
                );

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}