package com.carolina.app.autotrack.controller;

import com.carolina.app.autotrack.model.Vehicle;
import com.carolina.app.autotrack.dto.VehicleRequest;
import com.carolina.app.autotrack.dto.VehicleResponse;
import com.carolina.app.autotrack.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicles")
@Validated
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        List<VehicleResponse> responseDTOList = vehicles.stream()
                .map(VehicleResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(responseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getVehicleById(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        return ResponseEntity.ok().body(new VehicleResponse(vehicle));
    }

    @PostMapping
    public ResponseEntity<VehicleResponse> createVehicle(@RequestBody @Valid VehicleRequest vehicleNew) {
        Vehicle entity = vehicleService.saveVehicle(vehicleNew.toEntity());
        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(new VehicleResponse(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponse> updateVehicle(@PathVariable Long id, @RequestBody @Valid VehicleRequest vehicleRequest) {
        Vehicle updatedVehicle  = vehicleService.updateVehicle(id, vehicleRequest.toEntity());
        return ResponseEntity.ok(new VehicleResponse(updatedVehicle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}


