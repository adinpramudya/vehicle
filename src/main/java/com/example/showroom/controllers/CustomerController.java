package com.example.showroom.controllers;


import com.example.showroom.entities.CustomerEntity;
import com.example.showroom.entities.VehicleEntity;
import com.example.showroom.exceptions.ClientException;
import com.example.showroom.exceptions.NotFoundException;
import com.example.showroom.models.CustomerModel;
import com.example.showroom.models.ResponseModel;
import com.example.showroom.models.VehicleModel;
import com.example.showroom.repository.CustomerRepository;
import com.example.showroom.services.CustomerService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<ResponseModel> postCustomerController(@RequestBody CustomerModel customerModel){
        try {
            CustomerEntity customer = customerService.postVehicle(customerModel);
            ResponseModel res = new ResponseModel();
            res.setMsg("New Customer Successfully Added");
            res.setData(customer);
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

    @GetMapping(value = "/customers")
    public ResponseEntity<ResponseModel> getAllCustomerController() {
        try {
            // Request

            List<CustomerEntity> customers = customerService.getAllCustomer();

            ResponseModel response = new ResponseModel();
            response.setMsg("Request Successfully");
            response.setData(customers);
            return ResponseEntity.ok(response);

        } catch (Exception e) {

            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on out server.");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }


    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<ResponseModel> getCustomerById(@PathVariable Long id) {
        try {
            CustomerEntity customer = customerService.findById(id);

            ResponseModel response = new ResponseModel();
            response.setMsg("Request Successfully");
            response.setData(customer);
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

    @PatchMapping(value = "/customers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResponseModel> patchCustomerController(
            @PathVariable(value = "id", required = false) final Long id,
            @NotNull @RequestBody CustomerModel customerModel) {
        try {


            CustomerEntity vehicle = customerService.edit(customerModel);

            ResponseModel response = new ResponseModel();
            response.setMsg("Customer is Successfully update");
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

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<ResponseModel> deleteCustomerController(@PathVariable Long id) {
        try {
            CustomerEntity customer = customerService.delete(id);

            ResponseModel response = new ResponseModel();
            response.setMsg("customer is successfully deleted");
            response.setData(customer);
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
