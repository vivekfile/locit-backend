package com.vaya.locit.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vaya.locit.api.entity.Brand;
import com.vaya.locit.api.service.BrandService;

@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable int id) {

        Brand brand = brandService.getBrandById(id);

        if (brand == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(brand);
    }

    @GetMapping("/category/{categoryId}")
    public List<Brand> getBrandsByCategory(
            @PathVariable int categoryId) {

        return brandService.getBrandsByCategory(categoryId);
    }

    @PostMapping
    public Brand createBrand(@RequestBody Brand brand) {
        return brandService.createBrand(brand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(
            @PathVariable int id,
            @RequestBody Brand brand) {

        Brand updatedBrand = brandService.updateBrand(id, brand);

        if (updatedBrand == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedBrand);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable int id) {

        boolean deleted = brandService.deleteBrand(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
