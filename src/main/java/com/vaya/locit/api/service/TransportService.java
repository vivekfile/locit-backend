package com.vaya.locit.api.service;

import com.vaya.locit.api.entity.Transport;
import com.vaya.locit.api.repository.TransportRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {

    private final TransportRepository transportRepository;

    public TransportService(
            TransportRepository transportRepository) {

        this.transportRepository = transportRepository;
    }

    public List<Transport> getAllTransports() {

        return transportRepository.findAll();
    }

    public Transport getTransportById(int id) {

        return transportRepository
                .findById(id)
                .orElse(null);
    }

    public List<Transport> getTransportsBySeller(
            int sellerId) {

        return transportRepository
                .findBySellerUserId(sellerId);
    }

    public List<Transport> getTransportsByVehicleType(
            String vehicleType) {

        return transportRepository
                .findByVehicleTypeIgnoreCase(
                        vehicleType
                );
    }

    public List<Transport> getTransportsByServiceType(
            String serviceType) {

        return transportRepository
                .findByServiceTypeIgnoreCase(
                        serviceType
                );
    }

    public List<Transport> searchByLocation(
            String location) {

        return transportRepository
                .findByLocationContainingIgnoreCase(
                        location
                );
    }

    public List<Transport> getTransportsByStatus(
            Transport.TransportStatus status) {

        return transportRepository
                .findByStatus(status);
    }

    public List<Transport> getAvailableVehicles(
            String vehicleType) {

        return transportRepository
                .findByVehicleTypeIgnoreCaseAndStatus(
                        vehicleType,
                        Transport.TransportStatus.Available
                );
    }

    public Transport createTransport(
            Transport transport) {

        if (transport.getPrice() != null &&
                transport.getPrice() < 0) {

            throw new RuntimeException(
                    "Price cannot be negative"
            );
        }

        if (transport.getStatus() == null) {

            transport.setStatus(
                    Transport.TransportStatus.Available
            );
        }

        return transportRepository.save(transport);
    }

    public Transport updateTransport(
            int id,
            Transport transport) {

        Transport existingTransport =
                transportRepository
                        .findById(id)
                        .orElse(null);

        if (existingTransport == null) {
            return null;
        }

        existingTransport.setSeller(
                transport.getSeller()
        );

        existingTransport.setVehicleType(
                transport.getVehicleType()
        );

        existingTransport.setServiceType(
                transport.getServiceType()
        );

        existingTransport.setOwnerName(
                transport.getOwnerName()
        );

        existingTransport.setContact(
                transport.getContact()
        );

        existingTransport.setLocation(
                transport.getLocation()
        );

        existingTransport.setPrice(
                transport.getPrice()
        );

        existingTransport.setStatus(
                transport.getStatus()
        );

        return transportRepository.save(
                existingTransport
        );
    }

    public boolean deleteTransport(int id) {

        if (!transportRepository.existsById(id)) {
            return false;
        }

        transportRepository.deleteById(id);

        return true;
    }
}