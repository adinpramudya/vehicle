package com.example.showroom.models;

import com.example.showroom.models.enums.StatusVehicle;
import com.example.showroom.models.enums.TypeVehicle;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class VehicleModel {
    private Long id;
    private String name;
    private String merk;
    private String type;
    @Enumerated(EnumType.STRING)
    private TypeVehicle typeVehicle;
    private BigDecimal sellingPrice;
    private BigDecimal purchasePrice;
    @Enumerated(EnumType.STRING)
    private StatusVehicle status;
    private Boolean isActive;


}
