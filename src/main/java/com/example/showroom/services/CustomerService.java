package com.example.showroom.services;

import com.example.showroom.entities.CustomerEntity;
import com.example.showroom.entities.VehicleEntity;
import com.example.showroom.exceptions.ClientException;
import com.example.showroom.exceptions.NotFoundException;
import com.example.showroom.models.CustomerModel;
import com.example.showroom.models.VehicleModel;
import com.example.showroom.repository.CustomerRepository;
import com.example.showroom.repository.specs.CustomerSpec;
import com.example.showroom.repository.specs.VehicleSpec;
import com.example.showroom.validators.CustomerValidator;
import com.example.showroom.validators.VehicleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements Serializable {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerEntity postVehicle(CustomerModel customerModel) throws ClientException {
        System.out.println(customerModel.getEmail());
        CustomerValidator.notNullCheckAddress(customerModel.getAddress());
        CustomerValidator.notNullCheckCustomerName(customerModel.getName());
        CustomerValidator.notNullCheckEmail(customerModel.getEmail());
        CustomerValidator.notNullCheckGender(customerModel.getGender());
        CustomerValidator.notNullCheckIdentityType(customerModel.getIdentityType());
        CustomerValidator.notNullCheckNumberIdentity(customerModel.getNumberIdentity());
        CustomerValidator.notNullCheckPhoneNumber(customerModel.getPhoneNumber());

        CustomerEntity customer = new CustomerEntity();

        customer.setAddress(customerModel.getAddress());
        customer.setGender(customerModel.getGender());
        customer.setEmail(customerModel.getEmail());
        customer.setIdentityType(customerModel.getIdentityType());
        customer.setNumberIdentity(customerModel.getNumberIdentity());
        customer.setPhoneNumber(customerModel.getPhoneNumber());
        customer.setName(customerModel.getName());
        customer.setIsActive(true);
        customer.setCreatedBy("admin");
        customer.setCreatedDate(Instant.now());

        customerRepository.save(customer);

        return customer;
    }

    public List<CustomerEntity> getAllCustomer() {
        List<CustomerEntity> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);

        return customers;
    }

    public List<CustomerEntity> findAllByCriteria(CustomerModel customerModel) {
        List<CustomerEntity> customers = new ArrayList<>();
        CustomerSpec specs = new CustomerSpec(customerModel);
        customerRepository.findAll(specs).forEach(customers::add);

        return customers;
    }

    public CustomerEntity findById(Long id) throws ClientException, NotFoundException {
        // validation
        VehicleValidator.notNullCheckId(id);

        // process
        CustomerEntity vehicle = customerRepository.findById(id).orElse(null);
        CustomerValidator.nullCheckObject(vehicle);

        return vehicle;
    }

    public CustomerEntity edit(CustomerModel customerModel) throws ClientException, NotFoundException {
        // validation
        VehicleValidator.notNullCheckId(customerModel.getId());


        if (!customerRepository.existsById(customerModel.getId())) {
            throw new NotFoundException("Cannot find customer with id: " + customerModel.getId());
        }

        // process
        CustomerEntity customer = new CustomerEntity();
        customer = findById(customerModel.getId());

        if (customerModel.getName() != null) {
            customer.setName(customerModel.getName());
        }
        if (customerModel.getIdentityType() != null) {
            customer.setIdentityType(customerModel.getIdentityType());
        }

        if (customerModel.getGender() != null) {
            customer.setGender(customerModel.getGender());
        }

        if (customerModel.getNumberIdentity() != null) {
            customer.setNumberIdentity(customerModel.getNumberIdentity());

        }
        if (customerModel.getAddress() != null) {
            customer.setAddress(customerModel.getAddress());
        }
        if (customerModel.getEmail() != null) {
            customer.setEmail(customerModel.getEmail());
        }
        if (customerModel.getPhoneNumber() != null) {
            customer.setPhoneNumber(customerModel.getPhoneNumber());
        }


        customer.setLastModifiedBy("admin");
        customer.setLastModifiedDate(Instant.now());

        return customerRepository.save(customer);
    }

    public CustomerEntity delete(Long id) throws ClientException, NotFoundException {

        if (!customerRepository.existsById(id)) {
            throw new NotFoundException("Cannot find customer with id :" + id);
        }

        // process
        CustomerEntity customer = new CustomerEntity();
        customer = findById(id);

        if (customer.getIsActive() == false) {
            throw new ClientException("Customer id (" + id + ") is already been deleted.");
        }

        customer.setIsActive(false);
        customer.setLastModifiedBy("admin");
        customer.setLastModifiedDate(Instant.now());

        customerRepository.save(customer);

        return customer;
    }
}
