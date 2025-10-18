package org.tinnova.vehicles.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.tinnova.vehicles.database.entity.Vehicle;
import org.tinnova.vehicles.dto.VehicleDto;
import org.tinnova.vehicles.dto.VehicleFilterDto;
import org.tinnova.vehicles.service.VehicleService;

import java.util.List;

@Path("/vehicle")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehicleResource {

    @Inject
    VehicleService vehicleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vehicle> findAll() {
        return vehicleService.getAllVehicles();
    }

    @GET
    @Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByFilter(
        @QueryParam("brand") String brand,
        @QueryParam("sold") Boolean sold,
        @QueryParam("decade") Integer decade,
        @QueryParam("registeredLastWeek") Boolean registeredLastWeek,
        @QueryParam("page") int page,
        @QueryParam("size") int size) {

        VehicleFilterDto vehicleFilterDto = new VehicleFilterDto(brand, sold, decade, registeredLastWeek, page, size);
        List<Vehicle> vehicles = vehicleService.getVehiclesByFilter(vehicleFilterDto);
        return Response.ok(vehicles).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Vehicle vehicle = vehicleService.getById(id);
        return Response.ok(vehicle).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(VehicleDto vehicleDto) {
        VehicleDto vehicle = vehicleService.create(vehicleDto);
        return Response.ok(vehicle).build();
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(VehicleDto vehicleDto) {
        VehicleDto vehicle = vehicleService.update(vehicleDto);
        return Response.ok(vehicle).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        vehicleService.delete(id);
        return Response.status(Response.Status.OK).build();
    }
}