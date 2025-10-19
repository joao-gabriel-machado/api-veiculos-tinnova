package org.tinnova.vehicles.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.tinnova.vehicles.dto.VehicleDto;
import org.tinnova.vehicles.dto.VehicleFilterDto;
import org.tinnova.vehicles.dto.VehiclePatchDto;
import org.tinnova.vehicles.dto.VehiclesPerDecadeDto;
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
    public List<VehicleDto> findAll() {
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

        VehicleFilterDto vehicleFilterDto = new VehicleFilterDto(
            brand,
            sold,
            decade,
            registeredLastWeek,
            page,
            size
        );
        return Response.ok(vehicleService.getVehiclesByFilter(vehicleFilterDto)).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(vehicleService.getById(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(VehicleDto vehicleDto) {
        return Response.ok(vehicleService.create(vehicleDto)).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, VehicleDto vehicleDto) {
        return Response.ok(vehicleService.fullUpdate(id, vehicleDto)).build();
    }

    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response partialUpdate(@PathParam("id") Long id, VehiclePatchDto vehiclePatchDto) {
        return Response.ok(vehicleService.partialUpdate(id, vehiclePatchDto)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        vehicleService.delete(id);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/unsold-count")
    public Response getUnsoldCount() {
        return Response.ok(vehicleService.getUnsoldVehicleCount()).build();
    }

    @GET
    @Path("/distribution-by-decade")
    public Response getDistributionByDecade() {
        return Response.ok(vehicleService.getDistributionByDecade()).build();
    }

    @GET
    @Path("/distribution-by-brand")
    public Response getDistributionByBrand() {
        return Response.ok(vehicleService.getDistributionByBrand()).build();
    }

    @GET
    @Path("/registered-last-week")
    public Response getRegisteredLastWeek() {
        return Response.ok(vehicleService.getRegisteredLastWeek()).build();
    }
}