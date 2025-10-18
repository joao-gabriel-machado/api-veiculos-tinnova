package org.tinnova.vehicles.dto;

public record VehiclePatchDto(
    String brand,
    boolean sold,
    Integer decade,
    boolean registeredLasWeek,
    int page,
    int size
) {}
