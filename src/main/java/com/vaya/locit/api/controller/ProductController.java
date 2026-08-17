package com.vaya.locit.api.controller;

import com.vaya.locit.api.entity.Product;
import com.vaya.locit.api.service.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> products =
                productService.getAllProducts();

        return ResponseEntity.ok(products);
    }

    // GET /products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable int id) {

        Product product =
                productService.getProductById(id);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    // GET /products/brand/{brandId}
    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<Product>> getProductsByBrand(
            @PathVariable int brandId) {

        List<Product> products =
                productService.getProductsByBrand(brandId);

        return ResponseEntity.ok(products);
    }

    // GET /products/category/{categoryId}
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(
            @PathVariable int categoryId) {

        List<Product> products =
                productService.getProductsByCategory(categoryId);

        return ResponseEntity.ok(products);
    }

    // GET /products/shop/{shopId}
    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<Product>> getProductsByShop(
            @PathVariable int shopId) {

        List<Product> products =
                productService.getProductsByShop(shopId);

        return ResponseEntity.ok(products);
    }

    // GET /products/brand/{brandId}/available
    @GetMapping("/brand/{brandId}/available")
    public ResponseEntity<List<Product>> getAvailableProductsByBrand(
            @PathVariable int brandId) {

        List<Product> products =
                productService.getAvailableProductsByBrand(brandId);

        return ResponseEntity.ok(products);
    }

    // GET /products/search?name=phone
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam String name) {

        List<Product> products =
                productService.searchProducts(name);

        return ResponseEntity.ok(products);
    }

    // POST /products
    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestBody Product product) {

        Product createdProduct =
                productService.createProduct(product);

        return ResponseEntity.ok(createdProduct);
    }

    // PUT /products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int id,
            @RequestBody Product product) {

        Product updatedProduct =
                productService.updateProduct(id, product);

        if (updatedProduct == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE /products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable int id) {

        boolean deleted =
                productService.deleteProduct(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}