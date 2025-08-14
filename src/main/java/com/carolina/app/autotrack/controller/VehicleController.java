package com.carolina.app.autotrack.controller;

import com.carolina.app.autotrack.model.Vehicle;
import com.carolina.app.autotrack.model.dto.VehicleRequest;
import com.carolina.app.autotrack.model.dto.VehicleResponse;
import com.carolina.app.autotrack.service.VehicleService;
import jakarta.validation.constraints.NotNull;
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
@RequestMapping("/vehicle")
@Validated
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @SuppressWarnings("NullableProblems")
    @PostMapping
    public ResponseEntity<@NotNull VehicleResponse> createVehicle(@RequestBody @Valid VehicleRequest vehicleNew) {
        Vehicle entity = vehicleService.saveVehicle(vehicleNew.toEntity());
        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(new VehicleResponse(entity));
    }

    @SuppressWarnings("NullableProblems")
    @GetMapping("/{id}")
    public ResponseEntity<@NotNull VehicleResponse> getVehicleById(@PathVariable long id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        return ResponseEntity.ok().body(new VehicleResponse(vehicle));
    }

    @SuppressWarnings("NullableProblems")
    @GetMapping
    public ResponseEntity<@NotNull List<VehicleResponse>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        List<VehicleResponse> responseDTOList = vehicles.stream()
                .map(VehicleResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(responseDTOList);
    }

    @SuppressWarnings("NullableProblems")
    @PutMapping
    public ResponseEntity<@NotNull VehicleResponse> updateVehicle(@RequestBody @Valid VehicleRequest vehicleNew) {
        Vehicle vehicle = vehicleService.updateVehicle(vehicleNew.toEntity().getId(), vehicleNew.toEntity());
        return ResponseEntity.ok().body(new VehicleResponse(vehicle));
    }

    @DeleteMapping("{/id]")
    public ResponseEntity<Void> deleteVehicle(@RequestBody @Valid VehicleRequest vehicleDeleted) {
        vehicleService.deleteVehicle(vehicleDeleted.toEntity().getId());
        return ResponseEntity.noContent().build();
    }
}


