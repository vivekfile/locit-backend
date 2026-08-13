package com.vaya.locit.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vaya.locit.api.entity.Brand;
import com.vaya.locit.api.repository.BrandRepository;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(int id) {
        return brandRepository.findById(id).orElse(null);
    }

    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public List<Brand> getBrandsByCategory(int categoryId) {
        return brandRepository.findByCategoryCategoryId(categoryId);
    }

    public Brand updateBrand(int id, Brand brand) {

        Brand existingBrand = brandRepository.findById(id).orElse(null);

        if (existingBrand == null) {
            return null;
        }

        existingBrand.setBrandName(brand.getBrandName());
        existingBrand.setLogo(brand.getLogo());
        existingBrand.setCategory(brand.getCategory());

        return brandRepository.save(existingBrand);
    }

    public boolean deleteBrand(int id) {

        if (!brandRepository.existsById(id)) {
            return false;
        }

        brandRepository.deleteById(id);
        return true;
    }
}
