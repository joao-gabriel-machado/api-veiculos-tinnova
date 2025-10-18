package org.tinnova.vehicles.database.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.tinnova.vehicles.database.entity.Vehicle;
import org.tinnova.vehicles.dto.VehicleFilterDto;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class VehiclesRepository implements PanacheRepository<Vehicle> {
    public List<Vehicle> findAllVehicles() {
        return listAll();
    }

    public List<Vehicle> findVehiclesByFilter(VehicleFilterDto vehicleFilterDto) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Vehicle> cq = cb.createQuery(Vehicle.class);
        Root<Vehicle> root = cq.from(Vehicle.class);

        List<Predicate> predicates = new ArrayList<>();

        if (vehicleFilterDto.brand() != null && !vehicleFilterDto.brand().trim().isEmpty()) {
            String value = "%" + vehicleFilterDto.brand().trim().toLowerCase() + "%";
            predicates.add(cb.like(cb.lower(root.get("brand")), value));
        }

        if (vehicleFilterDto.sold() != null) {
            predicates.add(cb.equal(root.get("sold"), vehicleFilterDto.sold()));
        }

        if (vehicleFilterDto.decade() != null) {
            int startYear = vehicleFilterDto.decade();
            int endYear = startYear + 9;
            predicates.add(cb.between(root.get("yearManufacture"), startYear, endYear));
        }

        if (Boolean.TRUE.equals(vehicleFilterDto.registeredLasWeek())) {
            Instant weekAgo = Instant.now().minus(7, ChronoUnit.DAYS);
            predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), weekAgo));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.asc(root.get("id")));

        TypedQuery<Vehicle> query = em.createQuery(cq);

        int currentPage = Math.max(vehicleFilterDto.page(), 1);
        query.setFirstResult((currentPage - 1) * vehicleFilterDto.size());
        query.setMaxResults(vehicleFilterDto.size());

        return query.getResultList();
    }
}