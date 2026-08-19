package com.vaya.locit.api.repository;

import com.vaya.locit.api.entity.Payment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository
        extends JpaRepository<Payment, Integer> {

    Optional<Payment> findByOrderOrderId(int orderId);

    boolean existsByOrderOrderId(int orderId);
}