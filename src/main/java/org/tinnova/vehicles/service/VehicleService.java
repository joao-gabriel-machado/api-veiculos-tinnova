package org.tinnova.vehicles.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.tinnova.vehicles.database.entity.Vehicle;
import org.tinnova.vehicles.database.repository.VehiclesRepository;
import org.tinnova.vehicles.dto.VehicleDto;
import org.tinnova.vehicles.dto.VehicleFilterDto;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class VehicleService {

    @Inject
    VehiclesRepository vehiclesRepository;

    public List<Vehicle> getAllVehicles() {
        return vehiclesRepository.findAllVehicles();
    }

    public List<Vehicle> getVehiclesByFilter(VehicleFilterDto vehicleFilterDto) {
        return vehiclesRepository.findVehiclesByFilter(vehicleFilterDto);
    }

    public Vehicle getById(Long id) {
        return vehiclesRepository.findById(id);
    }

    @Transactional
    public VehicleDto create(VehicleDto vehicleDto) {
        Vehicle vehicle = new Vehicle();

        vehiclesRepository.persist(vehicle);

        return VehicleDto.from(vehicle);
    }

    @Transactional
    public VehicleDto update(VehicleDto vehicleDto) {
        Vehicle existingVehicle = vehiclesRepository.findById(vehicleDto.id());
        if (existingVehicle == null) {
            throw new NotFoundException("Entity not found with id: " + vehicleDto.id());
        }

        existingVehicle.setBrand(vehicleDto.brand());
        existingVehicle.setYearManufacture(vehicleDto.yearManufacture());
        existingVehicle.setDescription(vehicleDto.description());
        existingVehicle.setSold(vehicleDto.sold());
        existingVehicle.setUpdatedAt(LocalDate.now());

        return VehicleDto.from(existingVehicle);
    }

    @Transactional
    public void delete(Long id) {
        Vehicle existingVehicle = vehiclesRepository.findById(id);
        if (existingVehicle == null) {
            throw new NotFoundException("Entity not found with id: " + id);
        }
        vehiclesRepository.deleteById(id);
    }

}
