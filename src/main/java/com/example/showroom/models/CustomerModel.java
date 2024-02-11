package com.example.showroom.models;

import com.example.showroom.models.enums.GenderType;
import com.example.showroom.models.enums.IdentityType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerModel {
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private String address;
    private String phoneNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private IdentityType identityType;
    private String numberIdentity;
}
