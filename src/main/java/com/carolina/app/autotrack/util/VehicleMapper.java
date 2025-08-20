package com.carolina.app.autotrack.util;

import com.carolina.app.autotrack.dto.vehicle.VehiclePatchRequest;
import com.carolina.app.autotrack.dto.vehicle.VehicleRequest;
import com.carolina.app.autotrack.dto.vehicle.VehicleResponse;
import com.carolina.app.autotrack.dto.vehicle.VehicleSummaryResponse;
import com.carolina.app.autotrack.model.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    private final FuelRecordMapper fuelRecordMapper;

    public VehicleMapper(FuelRecordMapper fuelRecordMapper) {
        this.fuelRecordMapper = fuelRecordMapper;
    }

    public Vehicle toEntity(VehicleRequest vehicleRequest){
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(vehicleRequest.brand());
        vehicle.setModel(vehicleRequest.model());
        vehicle.setYear(vehicleRequest.year());
        return vehicle;
    }

    public VehicleResponse toResponse(Vehicle vehicle){
        //usa o metodo do fuel record mapper para converter a lista de fuelrecords em fuelrecords summarys
        var fuelRecordsSummary = fuelRecordMapper.toSummaryResponseList(vehicle.getFuelRecords());

        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getYear(),
                fuelRecordsSummary // A lista de DTOs resumidos
        );
    }

    public static VehicleSummaryResponse toSummaryResponse(Vehicle vehicle){
        return new VehicleSummaryResponse(
                vehicle.getId(),
                vehicle.getBrand(),
                vehicle.getModel()
        );
    }

    public void patchEntity(VehiclePatchRequest request, Vehicle vehicleToUpdate) {
        if (request.brand() != null) {
            vehicleToUpdate.setBrand(request.brand());
        }
        if (request.model() != null) {
            vehicleToUpdate.setModel(request.model());
        }
        if (request.year() != null) {
            vehicleToUpdate.setYear(request.year());
        }
    }
}
