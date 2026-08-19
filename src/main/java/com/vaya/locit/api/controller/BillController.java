package com.vaya.locit.api.controller;

import com.vaya.locit.api.entity.Bill;
import com.vaya.locit.api.service.BillService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    // GET /bills
    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {

        return ResponseEntity.ok(
                billService.getAllBills()
        );
    }

    // GET /bills/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(
            @PathVariable int id) {

        Bill bill =
                billService.getBillById(id);

        if (bill == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bill);
    }

    // GET /bills/buyer/{buyerId}
    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<List<Bill>> getBillsByBuyer(
            @PathVariable int buyerId) {

        return ResponseEntity.ok(
                billService.getBillsByBuyer(buyerId)
        );
    }

    // GET /bills/shop/{shopId}
    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<Bill>> getBillsByShop(
            @PathVariable int shopId) {

        return ResponseEntity.ok(
                billService.getBillsByShop(shopId)
        );
    }

    // POST /bills
    @PostMapping
    public ResponseEntity<Bill> createBill(
            @RequestBody Bill bill) {

        Bill createdBill =
                billService.createBill(bill);

        return ResponseEntity.ok(createdBill);
    }

    // PUT /bills/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(
            @PathVariable int id,
            @RequestBody Bill bill) {

        Bill updatedBill =
                billService.updateBill(id, bill);

        if (updatedBill == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedBill);
    }

    // DELETE /bills/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(
            @PathVariable int id) {

        boolean deleted =
                billService.deleteBill(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
