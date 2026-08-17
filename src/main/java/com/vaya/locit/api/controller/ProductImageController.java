package com.vaya.locit.api.controller;

import com.vaya.locit.api.entity.ProductImage;
import com.vaya.locit.api.service.ProductImageService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-images")
public class ProductImageController {

    private final ProductImageService productImageService;

    public ProductImageController(
            ProductImageService productImageService) {

        this.productImageService = productImageService;
    }

    // GET /product-images
    @GetMapping
    public ResponseEntity<List<ProductImage>> getAllImages() {

        List<ProductImage> images =
                productImageService.getAllImages();

        return ResponseEntity.ok(images);
    }

    // GET /product-images/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProductImage> getImageById(
            @PathVariable int id) {

        ProductImage image =
                productImageService.getImageById(id);

        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(image);
    }

    // GET /product-images/product/{productId}
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductImage>> getImagesByProduct(
            @PathVariable int productId) {

        List<ProductImage> images =
                productImageService.getImagesByProduct(productId);

        return ResponseEntity.ok(images);
    }

    // POST /product-images
    @PostMapping
    public ResponseEntity<ProductImage> createImage(
            @RequestBody ProductImage productImage) {

        ProductImage createdImage =
                productImageService.createImage(productImage);

        return ResponseEntity.ok(createdImage);
    }

    // PUT /product-images/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ProductImage> updateImage(
            @PathVariable int id,
            @RequestBody ProductImage productImage) {

        ProductImage updatedImage =
                productImageService.updateImage(id, productImage);

        if (updatedImage == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedImage);
    }

    // DELETE /product-images/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(
            @PathVariable int id) {

        boolean deleted =
                productImageService.deleteImage(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}