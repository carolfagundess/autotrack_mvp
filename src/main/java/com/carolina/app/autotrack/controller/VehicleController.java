package com.carolina.app.autotrack.controller;

import com.carolina.app.autotrack.dto.vehicle.VehiclePatchRequest;
import com.carolina.app.autotrack.dto.vehicle.VehicleRequest;
import com.carolina.app.autotrack.dto.vehicle.VehicleResponse;
import com.carolina.app.autotrack.model.Vehicle;
import com.carolina.app.autotrack.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
@Validated
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    //AJUSTADO
    @PostMapping
    public ResponseEntity<VehicleResponse> createVehicle(@RequestBody @Valid VehicleRequest vehicleNew) {
        VehicleResponse response = vehicleService.save(vehicleNew);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    //AJUSTADO
    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {
        List<VehicleResponse> responses = vehicleService.getAll();
        return ResponseEntity.ok(responses);
    }

    //AJUSTADO
    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getVehicleById(@PathVariable Long id) {
        VehicleResponse response = vehicleService.getById(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponse> updateVehicle(@PathVariable Long id, @RequestBody @Valid VehicleRequest vehicleRequest) {
        VehicleResponse updatedVehicle  = vehicleService.update(id, vehicleRequest);
        return ResponseEntity.ok(updatedVehicle);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VehicleResponse> patchVehicle(@PathVariable Long id, @RequestBody VehiclePatchRequest vehicleRequest) {
        VehicleResponse updatedVechiVehicle = vehicleService.patch(id, vehicleRequest);
        return ResponseEntity.ok(updatedVechiVehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


