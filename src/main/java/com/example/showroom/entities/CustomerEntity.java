package com.example.showroom.entities;

import com.example.showroom.models.enums.GenderType;
import com.example.showroom.models.enums.IdentityType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "customer")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "sequence_generator", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderType gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "identity_type")
    private IdentityType identityType;

    @Column(name = "number_identity")
    private String numberIdentity;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name ="created_date")
    private Instant createdDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name ="last_modified_date")
    private Instant lastModifiedDate;

    @Column(name = "is_active")
    private Boolean isActive;
}
