package org.tinnova.vehicles.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.tinnova.vehicles.database.entity.Vehicle;
import org.tinnova.vehicles.database.repository.VehiclesRepository;
import org.tinnova.vehicles.dto.UnsoldCountDto;
import org.tinnova.vehicles.dto.VehicleDto;
import org.tinnova.vehicles.dto.VehicleFilterDto;
import org.tinnova.vehicles.dto.VehiclePatchDto;
import org.tinnova.vehicles.dto.VehiclesPerBrandDto;
import org.tinnova.vehicles.dto.VehiclesPerDecadeDto;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class VehicleService {

    @Inject
    VehiclesRepository vehiclesRepository;

    public List<VehicleDto> getAllVehicles() {
        return VehicleDto.from(vehiclesRepository.findAllVehicles());
    }

    public List<VehicleDto> getVehiclesByFilter(VehicleFilterDto vehicleFilterDto) {
        return VehicleDto.from(vehiclesRepository.findVehiclesByFilter(vehicleFilterDto));
    }

    public VehicleDto getById(Long id) {
        return VehicleDto.from(vehiclesRepository.findById(id));
    }

    @Transactional
    public VehicleDto create(VehicleDto vehicleDto) {
        Vehicle vehicle = new Vehicle();

        vehicle.setBrand(vehicleDto.brand());
        vehicle.setYearManufacture(vehicleDto.yearManufacture());
        vehicle.setDescription(vehicleDto.description());
        vehicle.setSold(vehicleDto.sold());

        vehicle.setCreatedAt(LocalDate.now());
        vehicle.setUpdatedAt(LocalDate.now());

        vehiclesRepository.persist(vehicle);

        return VehicleDto.from(vehicle);
    }

    @Transactional
    public VehicleDto fullUpdate(Long id, VehicleDto vehicleDto) {
        Vehicle existingVehicle = vehiclesRepository.findById(id);
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
    public VehicleDto partialUpdate(Long id, VehiclePatchDto vehiclePatchDto) {
        Vehicle existingVehicle = vehiclesRepository.findById(id);
        if (existingVehicle == null) {
            throw new NotFoundException("Entity not found with id: " + id);
        }

        if (vehiclePatchDto.brand() != null) {
            existingVehicle.setBrand(vehiclePatchDto.brand());
        }
        if (vehiclePatchDto.yearManufacture() != null) {
            existingVehicle.setYearManufacture(vehiclePatchDto.yearManufacture());
        }
        if (vehiclePatchDto.description() != null) {
            existingVehicle.setDescription(vehiclePatchDto.description());
        }
        if (vehiclePatchDto.sold() != null) {
            existingVehicle.setSold(vehiclePatchDto.sold());
        }

        existingVehicle.setUpdatedAt(LocalDate.now());

        return VehicleDto.from(existingVehicle);
    }

    @Transactional
    public void delete(Long id) {
        vehiclesRepository.deleteById(id);
    }

    public UnsoldCountDto getUnsoldVehicleCount() {
        long count = vehiclesRepository.countUnsold();
        return new UnsoldCountDto(count);
    }

    public List<VehiclesPerDecadeDto> getDistributionByDecade() {
        return vehiclesRepository.getCountByDecade();
    }

    public List<VehiclesPerBrandDto> getDistributionByBrand() {
        return vehiclesRepository.getCountByBrand();
    }

    public List<VehicleDto> getRegisteredLastWeek() {
        List<Vehicle> vehicles = vehiclesRepository.findRegisteredLastWeek();
        return VehicleDto.from(vehicles);
    }
}
