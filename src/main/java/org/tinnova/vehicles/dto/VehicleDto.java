package org.tinnova.vehicles.dto;

import org.tinnova.vehicles.database.entity.Vehicle;

import java.time.LocalDate;
import java.util.List;

public record VehicleDto(
    long id,
    String brand,
    int yearManufacture,
    String description,
    boolean sold,
    LocalDate createdAt,
    LocalDate updatedAt
) {
    public static VehicleDto from(Vehicle vehicle) {
        return new VehicleDto(
            vehicle.getId(),
            vehicle.getBrand(),
            vehicle.getYearManufacture(),
            vehicle.getDescription(),
            vehicle.isSold(),
            vehicle.getCreatedAt(),
            vehicle.getUpdatedAt()
        );
    }

    public static Vehicle from(VehicleDto vehicleDto) {
        return new Vehicle(
            vehicleDto.id(),
            vehicleDto.brand(),
            vehicleDto.yearManufacture(),
            vehicleDto.description(),
            vehicleDto.sold(),
            vehicleDto.createdAt(),
            vehicleDto.updatedAt()
        );
    }

    public static List<VehicleDto> from(List<Vehicle> vehicles) {
        return vehicles.stream()
            .map(VehicleDto::from)
            .toList();
    }
}
