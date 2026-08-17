package com.vaya.locit.api.service;

import com.vaya.locit.api.entity.Product;
import com.vaya.locit.api.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProductsByBrand(int brandId) {
        return productRepository.findByBrandBrandId(brandId);
    }

    public List<Product> getProductsByCategory(int categoryId) {
        return productRepository.findByCategoryCategoryId(categoryId);
    }

    public List<Product> getProductsByShop(int shopId) {
        return productRepository.findByShopShopId(shopId);
    }

    public List<Product> getAvailableProductsByBrand(int brandId) {
        return productRepository
                .findByBrandBrandIdAndIsAvailableTrue(brandId);
    }

    public List<Product> searchProducts(String productName) {
        return productRepository
                .findByProductNameContainingIgnoreCase(productName);
    }

    public Product updateProduct(int id, Product product) {

        Product existingProduct =
                productRepository.findById(id).orElse(null);

        if (existingProduct == null) {
            return null;
        }

        existingProduct.setShop(product.getShop());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setBrand(product.getBrand());

        existingProduct.setProductName(product.getProductName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        existingProduct.setIsAvailable(product.getIsAvailable());

        return productRepository.save(existingProduct);
    }

    public boolean deleteProduct(int id) {

        if (!productRepository.existsById(id)) {
            return false;
        }

        productRepository.deleteById(id);

        return true;
    }
}