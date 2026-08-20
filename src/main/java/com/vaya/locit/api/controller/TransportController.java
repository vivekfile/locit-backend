package com.vaya.locit.api.controller;

import com.vaya.locit.api.entity.Transport;
import com.vaya.locit.api.service.TransportService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transports")
public class TransportController {

    private final TransportService transportService;

    public TransportController(
            TransportService transportService) {

        this.transportService = transportService;
    }

    // GET /transports
    @GetMapping
    public ResponseEntity<List<Transport>> getAllTransports() {

        return ResponseEntity.ok(
                transportService.getAllTransports()
        );
    }

    // GET /transports/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Transport> getTransportById(
            @PathVariable int id) {

        Transport transport =
                transportService.getTransportById(id);

        if (transport == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(transport);
    }

    // GET /transports/seller/{sellerId}
    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Transport>> getTransportsBySeller(
            @PathVariable int sellerId) {

        return ResponseEntity.ok(
                transportService.getTransportsBySeller(
                        sellerId
                )
        );
    }

    // GET /transports/vehicle?type=Truck
    @GetMapping("/vehicle")
    public ResponseEntity<List<Transport>> getByVehicleType(
            @RequestParam String type) {

        return ResponseEntity.ok(
                transportService.getTransportsByVehicleType(
                        type
                )
        );
    }

    // GET /transports/service?type=Rental
    @GetMapping("/service")
    public ResponseEntity<List<Transport>> getByServiceType(
            @RequestParam String type) {

        return ResponseEntity.ok(
                transportService.getTransportsByServiceType(
                        type
                )
        );
    }

    // GET /transports/location?name=Mahendragarh
    @GetMapping("/location")
    public ResponseEntity<List<Transport>> searchByLocation(
            @RequestParam String name) {

        return ResponseEntity.ok(
                transportService.searchByLocation(
                        name
                )
        );
    }

    // GET /transports/status/{status}
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Transport>> getByStatus(
            @PathVariable Transport.TransportStatus status) {

        return ResponseEntity.ok(
                transportService.getTransportsByStatus(
                        status
                )
        );
    }

    // GET /transports/available?type=Truck
    @GetMapping("/available")
    public ResponseEntity<List<Transport>> getAvailableVehicles(
            @RequestParam String type) {

        return ResponseEntity.ok(
                transportService.getAvailableVehicles(
                        type
                )
        );
    }

    // POST /transports
    @PostMapping
    public ResponseEntity<Transport> createTransport(
            @RequestBody Transport transport) {

        Transport createdTransport =
                transportService.createTransport(
                        transport
                );

        return ResponseEntity.ok(createdTransport);
    }

    // PUT /transports/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Transport> updateTransport(
            @PathVariable int id,
            @RequestBody Transport transport) {

        Transport updatedTransport =
                transportService.updateTransport(
                        id,
                        transport
                );

        if (updatedTransport == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedTransport);
    }

    // DELETE /transports/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransport(
            @PathVariable int id) {

        boolean deleted =
                transportService.deleteTransport(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}