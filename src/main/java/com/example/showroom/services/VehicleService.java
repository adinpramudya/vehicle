package com.example.showroom.services;

import com.example.showroom.entities.VehicleEntity;
import com.example.showroom.exceptions.ClientException;
import com.example.showroom.exceptions.NotFoundException;
import com.example.showroom.models.VehicleModel;
import com.example.showroom.repository.VehicleRepository;
import com.example.showroom.repository.specs.VehicleSpec;
import com.example.showroom.validators.VehicleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService implements Serializable {
    @Autowired
    private VehicleRepository vehicleRepository;

    public VehicleEntity postVehicle(VehicleModel vehicleModel) throws ClientException {
        VehicleValidator.notNullCheckStatus(vehicleModel.getStatus());
        VehicleValidator.notNullCheckName(vehicleModel.getName());
        VehicleValidator.notNullCheckMerk(vehicleModel.getMerk());
        VehicleValidator.notNullCheckTypeVehicle(vehicleModel.getTypeVehicle());
        VehicleValidator.notNullCheckPurchasePrice(vehicleModel.getPurchasePrice());
        VehicleValidator.notNullCheckSellingPrice(vehicleModel.getSellingPrice());
        VehicleEntity vehicle = new VehicleEntity();

        vehicle.setName(vehicleModel.getName());
        vehicle.setTypeVehicle(vehicleModel.getTypeVehicle());
        vehicle.setMerk(vehicleModel.getMerk());
        vehicle.setType(vehicleModel.getType());
        vehicle.setPurchasePrice(vehicleModel.getPurchasePrice());
        vehicle.setSellingPrice(vehicleModel.getSellingPrice());
        vehicle.setStatus(vehicleModel.getStatus());
        vehicle.setActive(true);
        vehicle.setCreatedBy("admin");
        vehicle.setCreatedDate(Instant.now());

        vehicleRepository.save(vehicle);

        return vehicle;
    }

    public List<VehicleEntity> getAllVehicle(VehicleModel vehicleModel) {
        List<VehicleEntity> vehicles = new ArrayList<>();
        VehicleSpec specs = new VehicleSpec(vehicleModel);
        vehicleRepository.findAll(specs).forEach(vehicles::add);

        return vehicles;
    }

    public List<VehicleEntity> findAllByCriteria(VehicleModel vehicleModel) {
        List<VehicleEntity> vehicles = new ArrayList<>();
        VehicleSpec specs = new VehicleSpec(vehicleModel);
        vehicleRepository.findAll(specs).forEach(vehicles::add);

        return vehicles;
    }

    public VehicleEntity findById(Long id) throws ClientException, NotFoundException {
        // validation
        VehicleValidator.notNullCheckId(id);

        // process
        VehicleEntity vehicle = vehicleRepository.findById(id).orElse(null);
        VehicleValidator.nullCheckObject(vehicle);

        return vehicle;
    }

    public VehicleEntity edit(VehicleModel vehicleModel) throws ClientException, NotFoundException {
        // validation
        VehicleValidator.notNullCheckId(vehicleModel.getId());


        if (!vehicleRepository.existsById(vehicleModel.getId())) {
            throw new NotFoundException("Cannot find product with id: " + vehicleModel.getId());
        }

        // process
        VehicleEntity vehicle = new VehicleEntity();
        vehicle = findById(vehicleModel.getId());

        if (vehicleModel.getName() != null) {
            vehicle.setName(vehicleModel.getName());
        }
        if (vehicleModel.getStatus() != null) {
            vehicle.setStatus(vehicleModel.getStatus());
        }

        if (vehicleModel.getMerk() != null) {
            vehicle.setMerk(vehicleModel.getMerk());
        }

        if (vehicleModel.getTypeVehicle() != null) {
            vehicle.setTypeVehicle(vehicleModel.getTypeVehicle());

        }
        if (vehicleModel.getSellingPrice() != null) {
            vehicle.setPurchasePrice(vehicleModel.getPurchasePrice());
        }
        if (vehicleModel.getPurchasePrice() != null) {
            vehicle.setSellingPrice(vehicleModel.getSellingPrice());
        }

        if (vehicleModel.getType() != null) {
            vehicle.setType(vehicleModel.getType());
        }
        vehicle.setLastModifiedBy("admin");
        vehicle.setLastModifiedDate(Instant.now());

        return vehicleRepository.save(vehicle);
    }

    public VehicleEntity delete(Long id) throws ClientException, NotFoundException {

        if (!vehicleRepository.existsById(id)) {
            throw new NotFoundException("Cannot find product with id :" + id);
        }

        // process
        VehicleEntity vehicle = new VehicleEntity();
        vehicle = findById(id);

        if (vehicle.getActive() == false) {
            throw new ClientException("Vehicle id (" + id + ") is already been deleted.");
        }

        vehicle.setActive(false);
        vehicle.setLastModifiedBy("admin");
        vehicle.setLastModifiedDate(Instant.now());

        vehicleRepository.save(vehicle);

        return vehicle;
    }

}
