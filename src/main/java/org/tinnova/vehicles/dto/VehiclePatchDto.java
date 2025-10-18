package org.tinnova.vehicles.dto;

public record VehiclePatchDto(
    String brand,
    Integer yearManufacture,
    String description,
    Boolean sold
) {}
