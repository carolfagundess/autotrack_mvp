package com.carolina.app.autotrack.controller;


import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordPatchRequest;
import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordRequest;
import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordResponse;
import com.carolina.app.autotrack.service.FuelRecordService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/fuelrecords")
@Validated
public class FuelRecordController {

    private final FuelRecordService fuelRecordService;

    public FuelRecordController(FuelRecordService fuelRecordService) {
        this.fuelRecordService = fuelRecordService;
    }

    @PostMapping
    public ResponseEntity<FuelRecordResponse> createFuelRecord(@Valid @RequestBody FuelRecordRequest request) {
        FuelRecordResponse fuelRecordResponse = fuelRecordService.save(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fuelRecordResponse.id()).toUri();
        return ResponseEntity.created(uri).body(fuelRecordResponse);
    }

    @GetMapping
    public ResponseEntity<List<FuelRecordResponse>> getAllFuelRecord() {
        List<FuelRecordResponse> fuelRecordResponses = fuelRecordService.getAll();
        return ResponseEntity.ok().body(fuelRecordResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuelRecordResponse> getFuelRecord(@PathVariable Long id) {
        FuelRecordResponse fuelRecordResponse = fuelRecordService.getById(id);
        return ResponseEntity.ok().body(fuelRecordResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuelRecordResponse>  updateFuelRecord(@PathVariable Long id, @Valid @RequestBody FuelRecordRequest request) {
        FuelRecordResponse fuelRecordResponse = fuelRecordService.update(id, request);
        return ResponseEntity.ok().body(fuelRecordResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FuelRecordResponse> patchFuelRecord(@PathVariable Long id, @RequestBody FuelRecordPatchRequest request) {
        FuelRecordResponse fuelRecordResponse = fuelRecordService.patch(id, request);
        return ResponseEntity.ok().body(fuelRecordResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        fuelRecordService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
