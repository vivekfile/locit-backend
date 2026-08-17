package com.vaya.locit.api.controller;

import com.vaya.locit.api.entity.Shop;
import com.vaya.locit.api.service.ShopService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    // GET /shops
    @GetMapping
    public ResponseEntity<List<Shop>> getAllShops() {

        List<Shop> shops = shopService.getAllShops();

        return ResponseEntity.ok(shops);
    }

    // GET /shops/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Shop> getShopById(
            @PathVariable int id) {

        Shop shop = shopService.getShopById(id);

        if (shop == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(shop);
    }

    // GET /shops/seller/{sellerId}
    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Shop>> getShopsBySeller(
            @PathVariable int sellerId) {

        List<Shop> shops =
                shopService.getShopsBySeller(sellerId);

        return ResponseEntity.ok(shops);
    }

    // GET /shops/city/{city}
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Shop>> getShopsByCity(
            @PathVariable String city) {

        List<Shop> shops =
                shopService.getShopsByCity(city);

        return ResponseEntity.ok(shops);
    }

    // GET /shops/state/{state}
    @GetMapping("/state/{state}")
    public ResponseEntity<List<Shop>> getShopsByState(
            @PathVariable String state) {

        List<Shop> shops =
                shopService.getShopsByState(state);

        return ResponseEntity.ok(shops);
    }

    // POST /shops
    @PostMapping
    public ResponseEntity<Shop> createShop(
            @RequestBody Shop shop) {

        Shop createdShop =
                shopService.createShop(shop);

        return ResponseEntity.ok(createdShop);
    }

    // PUT /shops/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Shop> updateShop(
            @PathVariable int id,
            @RequestBody Shop shop) {

        Shop updatedShop =
                shopService.updateShop(id, shop);

        if (updatedShop == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedShop);
    }

    // DELETE /shops/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShop(
            @PathVariable int id) {

        boolean deleted =
                shopService.deleteShop(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
