package com.example.showroom.controllers;

import com.example.showroom.entities.VehicleEntity;
import com.example.showroom.exceptions.ClientException;
import com.example.showroom.exceptions.NotFoundException;
import com.example.showroom.models.ResponseModel;
import com.example.showroom.models.VehicleModel;
import com.example.showroom.services.VehicleService;
import org.antlr.v4.runtime.misc.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    private static final Logger logger = LogManager.getLogger(VehicleController.class);;

    @PostMapping("/vehicle")
    public ResponseEntity<ResponseModel> postVehicleController(@RequestBody VehicleModel vehicleModel){
        try {
            System.out.println(vehicleModel);
            VehicleEntity vehicle = vehicleService.postVehicle(vehicleModel);
            ResponseModel res = new ResponseModel();
            res.setMsg("New Vehicle Successfully Added");
            res.setData(vehicle);
            return ResponseEntity.ok(res);
        }  catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on out server.");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/vehicle")
    public ResponseEntity<ResponseModel> getAllVehicleController() {
        try {
            // Request
            VehicleModel vehicle = new VehicleModel();
            vehicle.setIsActive(true);
            List<VehicleEntity> vehicles = vehicleService.getAllVehicle(vehicle);

            ResponseModel response = new ResponseModel();
            response.setMsg("Request Successfully");
            response.setData(vehicles);
            return ResponseEntity.ok(response);

        } catch (Exception e) {

            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on out server.");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }
//    @GetMapping(value = "/vehicle")
//    public ResponseEntity<ResponseModel> searchVehicleController(@RequestParam ProductModel productModel) {
//        try {
//            List<ProductEntity> products = productService.findAllByCriteria(productModel);
//
//            ResponseModel response = new ResponseModel();
//            response.setMsg("Request Successfully");
//            response.setData(products);
//            return ResponseEntity.ok(response);
//
//        } catch (Exception e) {
//
//            ResponseModel response = new ResponseModel();
//            response.setMsg("Sorry, there is a failure on aour server");
//            return ResponseEntity.internalServerError().body(response);
//        }
//    }

    @GetMapping(value = "/vehicle/{id}")
    public ResponseEntity<ResponseModel> getVehicleById(@PathVariable Long id) {
        try {
            VehicleEntity vehicle = vehicleService.findById(id);

            ResponseModel response = new ResponseModel();
            response.setMsg("Request Successfully");
            response.setData(vehicle);
            return ResponseEntity.ok(response);
        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (NotFoundException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {

            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, There is a failure on our server");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PatchMapping(value = "/vehicle/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResponseModel> putProductController(
            @PathVariable(value = "id", required = false) final Long id,
            @NotNull @RequestBody VehicleModel vehicleModel) {
        try {


            VehicleEntity vehicle = vehicleService.edit(vehicleModel);

            ResponseModel response = new ResponseModel();
            response.setMsg("Vehicle is Successfully update");
            response.setData(vehicle);
            return ResponseEntity.ok(response);
        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (NotFoundException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {

            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, There is a failure on our server");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<ResponseModel> deleteProductController(@PathVariable Long id) {
        try {
            VehicleEntity vehicle = vehicleService.delete(id);

            ResponseModel response = new ResponseModel();
            response.setMsg("Vehicle is successfully deleted");
            response.setData(vehicle);
            return ResponseEntity.ok(response);
        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (NotFoundException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {

            ResponseModel response = new ResponseModel();
            response.setMsg("Sorrt, There is a failure on our server");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
