package com.example.showroom.validators;

import com.example.showroom.exceptions.ClientException;
import com.example.showroom.exceptions.NotFoundException;
import com.example.showroom.models.enums.GenderType;
import com.example.showroom.models.enums.IdentityType;
import com.example.showroom.models.enums.StatusVehicle;
import com.example.showroom.models.enums.TypeVehicle;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerValidator {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void notNullCheckCustomerId(Long id) throws ClientException {
        if(id == null){
            throw new ClientException("ID is required");
        }
    }
    public static void notNullCheckCustomerName(String name) throws ClientException{
        if(name == null || name.length() <= 0){
            throw new ClientException("Name is required");
        }
    }

    public static void notNullCheckGender(GenderType gender) throws ClientException{
        if(gender == null){
            throw new ClientException("Gender is required");
        }
    }

    public static void notNullCheckAddress(String address) throws ClientException{
        if(address == null ){
            throw new ClientException("Address is required");
        }
    }
    public static void notNullCheckPhoneNumber(String phoneNumber) throws ClientException{
        if(phoneNumber == null){
            throw new ClientException("Phone number is required");
        }
    }

    public static void notNullCheckEmail(String email) throws ClientException{
        if(email == null){
            throw new ClientException("Email is required");
        }
    }

    public static void notNullCheckIdentityType(IdentityType identityType) throws ClientException{
        if(identityType == null){
            throw new ClientException("Identity Type is required");
        }
    }

    public static void notNullCheckNumberIdentity(String numberIdentity) throws ClientException{
        if(numberIdentity == null){
            throw new ClientException("Identity Type is required");
        }
    }

    public static void nullCheckObject(Object o) throws NotFoundException {
        if (o == null) {
            throw new NotFoundException("Vehicle is not found!");
        }
    }

    public static void validateEmail(String email) throws ClientException{
        if(isValidEmail(email)){
            throw new ClientException("Wrong format email!");
        }
    }
}
