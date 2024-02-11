package com.example.showroom.repository.specs;

import com.example.showroom.entities.VehicleEntity;
import com.example.showroom.models.VehicleModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class VehicleSpec implements Specification<VehicleEntity> {
    private VehicleModel vehicleModel;

    public VehicleSpec(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    @Override
    public Predicate toPredicate(Root<VehicleEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        Predicate p = cb.and();
        if (vehicleModel.getName() != null && vehicleModel.getName().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("name")), "%" + vehicleModel.getName().toLowerCase() + "%"));
        }

        if (vehicleModel.getType() != null && vehicleModel.getType().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("type")), "%" + vehicleModel.getType().toLowerCase() + "%"));

        }
        if (vehicleModel.getMerk() != null && vehicleModel.getMerk().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("merk")), "%" + vehicleModel.getMerk().toLowerCase() + "%"));

        }
        if (vehicleModel.getTypeVehicle() != null) {
            p.getExpressions().add(cb.equal(cb.lower(root.get("type_vehicle")), "%" + vehicleModel.getMerk().toLowerCase() + "%"));
        }

        p = cb.and(p, cb.equal(root.get("isActive"), true));
        p = cb.and(p, cb.isTrue(root.get("isActive")));

        cq.orderBy(cb.asc(root.get("id")));
        return p;

    }
}
