package com.vaya.locit.api.controller;

import com.vaya.locit.api.entity.Payment;
import com.vaya.locit.api.service.PaymentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(
            PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    // GET /payments
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {

        List<Payment> payments =
                paymentService.getAllPayments();

        return ResponseEntity.ok(payments);
    }

    // GET /payments/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(
            @PathVariable int id) {

        Payment payment =
                paymentService.getPaymentById(id);

        if (payment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(payment);
    }

    // GET /payments/order/{orderId}
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Payment> getPaymentByOrder(
            @PathVariable int orderId) {

        Payment payment =
                paymentService.getPaymentByOrder(orderId);

        if (payment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(payment);
    }

    // POST /payments
    @PostMapping
    public ResponseEntity<Payment> createPayment(
            @RequestBody Payment payment) {

        Payment createdPayment =
                paymentService.createPayment(payment);

        return ResponseEntity.ok(createdPayment);
    }

    // PATCH /payments/{id}/status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Payment> updatePaymentStatus(
            @PathVariable int id,
            @RequestParam Payment.PaymentStatus status,
            @RequestParam(required = false)
            String transactionId) {

        Payment updatedPayment =
                paymentService.updatePaymentStatus(
                        id,
                        status,
                        transactionId
                );

        if (updatedPayment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedPayment);
    }

    // DELETE /payments/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(
            @PathVariable int id) {

        boolean deleted =
                paymentService.deletePayment(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}