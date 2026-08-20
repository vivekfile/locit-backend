package com.vaya.locit.api.repository;

import com.vaya.locit.api.entity.Transport;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransportRepository
        extends JpaRepository<Transport, Integer> {

    List<Transport> findBySellerUserId(int sellerId);

    List<Transport> findByVehicleTypeIgnoreCase(
            String vehicleType
    );

    List<Transport> findByServiceTypeIgnoreCase(
            String serviceType
    );

    List<Transport> findByLocationContainingIgnoreCase(
            String location
    );

    List<Transport> findByStatus(
            Transport.TransportStatus status
    );

    List<Transport> findByVehicleTypeIgnoreCaseAndStatus(
            String vehicleType,
            Transport.TransportStatus status
    );
}