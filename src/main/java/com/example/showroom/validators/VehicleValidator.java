package com.example.showroom.validators;

import com.example.showroom.exceptions.ClientException;
import com.example.showroom.exceptions.NotFoundException;
import com.example.showroom.models.enums.StatusVehicle;
import com.example.showroom.models.enums.TypeVehicle;

import java.math.BigDecimal;
import java.math.BigInteger;

public class VehicleValidator {

    public static void notNullCheckId(Long id) throws ClientException{
        if(id == null){
            throw new ClientException("ID is required");
        }
    }
    public static void notNullCheckName(String name) throws ClientException{
        if(name == null || name.length() <= 0){
            throw new ClientException("Name is required");
        }
    }

    public static void notNullCheckMerk(String merk) throws ClientException{
        if(merk == null || merk.length() <= 0){
            throw new ClientException("Merk is required");
        }
    }

    public static void notNullCheckTypeVehicle(TypeVehicle typeVehicle) throws ClientException{
        if(typeVehicle == null ){
            throw new ClientException("Type Vehicle is required");
        }
    }
    public static void notNullCheckStatus(StatusVehicle status) throws ClientException{
        if(status == null){
            throw new ClientException("Status is required");
        }
    }

    public static void notNullCheckSellingPrice(BigDecimal sellPrice) throws ClientException{
        System.out.println(sellPrice);
        if(sellPrice == null || sellPrice.compareTo(BigDecimal.ZERO) < 0){
            throw new ClientException("Sell Price is required and must be greater than zero");
        }
    }

    public static void notNullCheckPurchasePrice(BigDecimal purchasePrice) throws ClientException{
        if(purchasePrice == null || purchasePrice.compareTo(BigDecimal.ZERO) < 0){
            throw new ClientException("Purchase Price is required and must be greater than zero");
        }
    }

    public static void nullCheckObject(Object o) throws NotFoundException {
        if (o == null) {
            throw new NotFoundException("Vehicle is not found!");
        }
    }

//    public static void validateCheckTypeVehicle(String typeVehicle) throws ClientException{
//        for (TypeVehicle type : TypeVehicle.values()){
//            if(!type.name().equalsIgnoreCase(typeVehicle)){
//                throw new ClientException("This type vehicle not found");
//            }
//        }
//    }
//    public static void validateCheckStatus(String status) throws ClientException{
//        for (StatusVehicle statusVehicle : StatusVehicle.values()){
//            if(!statusVehicle.name().equalsIgnoreCase(status)){
//                throw new ClientException("This status vehicle not found");
//            }
//        }
//    }


}
