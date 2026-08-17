package com.vaya.locit.api.service;

import com.vaya.locit.api.entity.ProductImage;
import com.vaya.locit.api.repository.ProductImageRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {

    private final ProductImageRepository productImageRepository;

    public ProductImageService(
            ProductImageRepository productImageRepository) {

        this.productImageRepository = productImageRepository;
    }

    public List<ProductImage> getAllImages() {
        return productImageRepository.findAll();
    }

    public ProductImage getImageById(int id) {
        return productImageRepository
                .findById(id)
                .orElse(null);
    }

    public List<ProductImage> getImagesByProduct(int productId) {
        return productImageRepository
                .findByProductProductId(productId);
    }

    public ProductImage createImage(ProductImage productImage) {
        return productImageRepository.save(productImage);
    }

    public ProductImage updateImage(
            int id,
            ProductImage productImage) {

        ProductImage existingImage =
                productImageRepository.findById(id).orElse(null);

        if (existingImage == null) {
            return null;
        }

        existingImage.setProduct(productImage.getProduct());
        existingImage.setImageUrl(productImage.getImageUrl());

        return productImageRepository.save(existingImage);
    }

    public boolean deleteImage(int id) {

        if (!productImageRepository.existsById(id)) {
            return false;
        }

        productImageRepository.deleteById(id);

        return true;
    }
}