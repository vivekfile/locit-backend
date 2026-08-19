package com.vaya.locit.api.service;

import com.vaya.locit.api.entity.Bill;
import com.vaya.locit.api.repository.BillRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Bill getBillById(int id) {

        return billRepository
                .findById(id)
                .orElse(null);
    }

    public List<Bill> getBillsByBuyer(int buyerId) {

        return billRepository
                .findByBuyerUserId(buyerId);
    }

    public List<Bill> getBillsByShop(int shopId) {

        return billRepository
                .findByShopShopId(shopId);
    }

    public Bill createBill(Bill bill) {

        if (bill.getTotalAmount() == null ||
                bill.getTotalAmount() < 0) {

            throw new RuntimeException(
                    "Total amount cannot be negative"
            );
        }

        return billRepository.save(bill);
    }

    public Bill updateBill(
            int id,
            Bill bill) {

        Bill existingBill =
                billRepository
                        .findById(id)
                        .orElse(null);

        if (existingBill == null) {
            return null;
        }

        existingBill.setShop(bill.getShop());
        existingBill.setBuyer(bill.getBuyer());
        existingBill.setTotalAmount(
                bill.getTotalAmount()
        );

        return billRepository.save(existingBill);
    }

    public boolean deleteBill(int id) {

        if (!billRepository.existsById(id)) {
            return false;
        }

        billRepository.deleteById(id);

        return true;
    }
}