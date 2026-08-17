package com.vaya.locit.api.service;

import com.vaya.locit.api.entity.Shop;
import com.vaya.locit.api.repository.ShopRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    public Shop getShopById(int id) {
        return shopRepository.findById(id).orElse(null);
    }

    public Shop createShop(Shop shop) {
        return shopRepository.save(shop);
    }

    public List<Shop> getShopsBySeller(int sellerId) {
        return shopRepository.findBySellerUserId(sellerId);
    }

    public List<Shop> getShopsByCity(String city) {
        return shopRepository.findByCity(city);
    }

    public List<Shop> getShopsByState(String state) {
        return shopRepository.findByState(state);
    }

    public Shop updateShop(int id, Shop shop) {

        Shop existingShop = shopRepository.findById(id).orElse(null);

        if (existingShop == null) {
            return null;
        }

        existingShop.setSeller(shop.getSeller());
        existingShop.setShopName(shop.getShopName());
        existingShop.setDescription(shop.getDescription());
        existingShop.setAddress(shop.getAddress());
        existingShop.setCity(shop.getCity());
        existingShop.setState(shop.getState());
        existingShop.setPincode(shop.getPincode());
        existingShop.setLatitude(shop.getLatitude());
        existingShop.setLongitude(shop.getLongitude());

        return shopRepository.save(existingShop);
    }

    public boolean deleteShop(int id) {

        if (!shopRepository.existsById(id)) {
            return false;
        }

        shopRepository.deleteById(id);

        return true;
    }
}
