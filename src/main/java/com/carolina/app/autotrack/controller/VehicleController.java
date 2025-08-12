package com.carolina.app.autotrack.controller;

import com.carolina.app.autotrack.model.Vehicle;
import com.carolina.app.autotrack.model.dto.VehicleRequestDTO;
import com.carolina.app.autotrack.model.dto.VehicleResponseDTO;
import com.carolina.app.autotrack.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


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

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<VehicleResponseDTO> createVehicle(@RequestBody @Valid VehicleRequestDTO vehicleNew) {
        Vehicle entity = vehicleService.saveVehicle(vehicleNew.toEntity());
        VehicleResponseDTO vehicleResponseDTO = new VehicleResponseDTO(entity);
        return ResponseEntity.ok().body(vehicleResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponseDTO>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        List<VehicleResponseDTO> responseDTOList = vehicles.stream()
                .map(VehicleResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(responseDTOList);
    }
}


