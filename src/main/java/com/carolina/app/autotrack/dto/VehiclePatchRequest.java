package com.carolina.app.autotrack.dto;

import com.carolina.app.autotrack.model.Vehicle;

public record VehiclePatchRequest(String brand, String model, Integer year, Long mileage) {
    public Vehicle toEntity() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setYear(year);
        vehicle.setMileage(mileage);
        return vehicle;
    }
}
