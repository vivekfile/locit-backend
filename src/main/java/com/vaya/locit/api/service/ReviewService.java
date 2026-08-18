package com.vaya.locit.api.service;

import com.vaya.locit.api.entity.Product;
import com.vaya.locit.api.entity.Review;
import com.vaya.locit.api.repository.ProductRepository;
import com.vaya.locit.api.repository.ReviewRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public ReviewService(
            ReviewRepository reviewRepository,
            ProductRepository productRepository) {

        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(int id) {

        return reviewRepository
                .findById(id)
                .orElse(null);
    }

    public List<Review> getReviewsByProduct(int productId) {

        return reviewRepository
                .findByProductProductIdOrderByCreatedAtDesc(
                        productId
                );
    }

    public List<Review> getReviewsByUser(int userId) {

        return reviewRepository
                .findByUserUserId(userId);
    }

    public Review createReview(Review review) {

        if (review.getRating() == null ||
                review.getRating() < 1 ||
                review.getRating() > 5) {

            throw new RuntimeException(
                    "Rating must be between 1 and 5"
            );
        }

        int productId =
                review.getProduct().getProductId();

        Product product =
                productRepository
                        .findById(productId)
                        .orElse(null);

        if (product == null) {
            throw new RuntimeException(
                    "Product not found"
            );
        }

        review.setProduct(product);

        return reviewRepository.save(review);
    }

    public Review updateReview(
            int id,
            Review review) {

        Review existingReview =
                reviewRepository
                        .findById(id)
                        .orElse(null);

        if (existingReview == null) {
            return null;
        }

        if (review.getRating() == null ||
                review.getRating() < 1 ||
                review.getRating() > 5) {

            throw new RuntimeException(
                    "Rating must be between 1 and 5"
            );
        }

        existingReview.setRating(
                review.getRating()
        );

        existingReview.setReviewText(
                review.getReviewText()
        );

        return reviewRepository.save(
                existingReview
        );
    }

    public boolean deleteReview(int id) {

        if (!reviewRepository.existsById(id)) {
            return false;
        }

        reviewRepository.deleteById(id);

        return true;
    }
}