package com.vaya.locit.api.controller;

import com.vaya.locit.api.entity.Review;
import com.vaya.locit.api.service.ReviewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(
            ReviewService reviewService) {

        this.reviewService = reviewService;
    }

    // GET /reviews
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {

        List<Review> reviews =
                reviewService.getAllReviews();

        return ResponseEntity.ok(reviews);
    }

    // GET /reviews/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(
            @PathVariable int id) {

        Review review =
                reviewService.getReviewById(id);

        if (review == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(review);
    }

    // GET /reviews/product/{productId}
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProduct(
            @PathVariable int productId) {

        List<Review> reviews =
                reviewService.getReviewsByProduct(
                        productId
                );

        return ResponseEntity.ok(reviews);
    }

    // GET /reviews/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUser(
            @PathVariable int userId) {

        List<Review> reviews =
                reviewService.getReviewsByUser(
                        userId
                );

        return ResponseEntity.ok(reviews);
    }

    // POST /reviews
    @PostMapping
    public ResponseEntity<Review> createReview(
            @RequestBody Review review) {

        Review createdReview =
                reviewService.createReview(review);

        return ResponseEntity.ok(createdReview);
    }

    // PUT /reviews/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(
            @PathVariable int id,
            @RequestBody Review review) {

        Review updatedReview =
                reviewService.updateReview(
                        id,
                        review
                );

        if (updatedReview == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedReview);
    }

    // DELETE /reviews/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable int id) {

        boolean deleted =
                reviewService.deleteReview(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}