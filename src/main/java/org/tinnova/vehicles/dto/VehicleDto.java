package org.tinnova.vehicles.dto;

import org.tinnova.vehicles.database.entity.Vehicle;

public record VehicleDto(
    long id,
    String brand,
    int yearManufacture,
    String description,
    boolean sold,
    String createdAt,
    String updatedAt
) {
    public static VehicleDto from(Vehicle vehicle) {
        return new VehicleDto(
            vehicle.getId(),
            vehicle.getBrand(),
            vehicle.getYearManufacture(),
            vehicle.getDescription(),
            vehicle.isSold(),
            vehicle.getCreatedAt().toString(),
            vehicle.getUpdatedAt().toString()
        );
    }
}
