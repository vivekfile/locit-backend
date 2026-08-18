package com.vaya.locit.api.service;

import com.vaya.locit.api.entity.Product;
import com.vaya.locit.api.entity.Wishlist;
import com.vaya.locit.api.repository.ProductRepository;
import com.vaya.locit.api.repository.WishlistRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;

    public WishlistService(
            WishlistRepository wishlistRepository,
            ProductRepository productRepository) {

        this.wishlistRepository = wishlistRepository;
        this.productRepository = productRepository;
    }

    public List<Wishlist> getAllWishlistItems() {
        return wishlistRepository.findAll();
    }

    public Wishlist getWishlistById(int id) {

        return wishlistRepository
                .findById(id)
                .orElse(null);
    }

    public List<Wishlist> getWishlistByUser(int userId) {

        return wishlistRepository
                .findByUserUserId(userId);
    }

    public Wishlist addToWishlist(Wishlist wishlist) {

        int userId =
                wishlist.getUser().getUserId();

        int productId =
                wishlist.getProduct().getProductId();

        Product product =
                productRepository
                        .findById(productId)
                        .orElse(null);

        if (product == null) {
            throw new RuntimeException(
                    "Product not found"
            );
        }

        if (wishlistRepository
                .existsByUserUserIdAndProductProductId(
                        userId,
                        productId
                )) {

            throw new RuntimeException(
                    "Product already exists in wishlist"
            );
        }

        wishlist.setProduct(product);

        return wishlistRepository.save(wishlist);
    }

    public boolean removeFromWishlist(int id) {

        if (!wishlistRepository.existsById(id)) {
            return false;
        }

        wishlistRepository.deleteById(id);

        return true;
    }

    public boolean removeProductFromWishlist(
            int userId,
            int productId) {

        Wishlist wishlist =
                wishlistRepository
                        .findByUserUserIdAndProductProductId(
                                userId,
                                productId
                        )
                        .orElse(null);

        if (wishlist == null) {
            return false;
        }

        wishlistRepository.delete(wishlist);

        return true;
    }
}