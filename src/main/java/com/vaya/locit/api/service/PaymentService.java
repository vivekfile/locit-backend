package com.vaya.locit.api.service;

import com.vaya.locit.api.entity.Order;
import com.vaya.locit.api.entity.Payment;
import com.vaya.locit.api.repository.OrderRepository;
import com.vaya.locit.api.repository.PaymentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            OrderRepository orderRepository) {

        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(int id) {

        return paymentRepository
                .findById(id)
                .orElse(null);
    }

    public Payment getPaymentByOrder(int orderId) {

        return paymentRepository
                .findByOrderOrderId(orderId)
                .orElse(null);
    }

    @Transactional
    public Payment createPayment(Payment payment) {

        if (payment.getOrder() == null) {
            throw new RuntimeException(
                    "Order is required"
            );
        }

        int orderId =
                payment.getOrder().getOrderId();

        Order order =
                orderRepository
                        .findById(orderId)
                        .orElse(null);

        if (order == null) {
            throw new RuntimeException(
                    "Order not found"
            );
        }

        if (paymentRepository
                .existsByOrderOrderId(orderId)) {

            throw new RuntimeException(
                    "Payment already exists for this order"
            );
        }

        if (payment.getAmount() == null ||
                payment.getAmount() <= 0) {

            throw new RuntimeException(
                    "Payment amount must be greater than 0"
            );
        }

        if (payment.getPaymentMethod() == null) {
            throw new RuntimeException(
                    "Payment method is required"
            );
        }

        /*
         * Attach the actual Order object.
         */
        payment.setOrder(order);

        /*
         * New payments start as Pending.
         */
        payment.setPaymentStatus(
                Payment.PaymentStatus.Pending
        );

        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment updatePaymentStatus(
            int id,
            Payment.PaymentStatus status,
            String transactionId) {

        Payment payment =
                paymentRepository
                        .findById(id)
                        .orElse(null);

        if (payment == null) {
            return null;
        }

        payment.setPaymentStatus(status);

        if (transactionId != null &&
                !transactionId.isBlank()) {

            payment.setTransactionId(
                    transactionId
            );
        }

        if (status == Payment.PaymentStatus.Success) {

            payment.setPaidAt(
                    LocalDateTime.now()
            );
        }

        return paymentRepository.save(payment);
    }

    public boolean deletePayment(int id) {

        if (!paymentRepository.existsById(id)) {
            return false;
        }

        paymentRepository.deleteById(id);

        return true;
    }
}