package org.tinnova.vehicles.dto;

public record VehicleFilterDto(
    String brand,
    Boolean sold,
    Integer decade,
    Boolean registeredLasWeek,
    int page,
    int size
) {}
