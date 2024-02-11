package com.example.showroom.repository.specs;

import com.example.showroom.entities.CustomerEntity;
import com.example.showroom.models.CustomerModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpec implements Specification<CustomerEntity> {
    private CustomerModel customerModel;

    public CustomerSpec(CustomerModel customerModel){
        super();
        this.customerModel = customerModel;
    }

    @Override
    public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        Predicate p = cb.and();

        // id criteria
        if (customerModel.getId() != null && customerModel.getId() != 0) {
            p.getExpressions().add(cb.equal(root.get("id"), customerModel.getId()));
        }

        // name criterid
        if (customerModel.getName() != null && customerModel.getName().length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("name")), "%" + customerModel.getName().toLowerCase() + "%"));
        }

        if (customerModel.getAddress() != null && customerModel.getAddress().length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("address")), "%" + customerModel.getAddress().toLowerCase() + "%"));
        }

        if (customerModel.getEmail() != null && customerModel.getEmail().length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("email")), "%" + customerModel.getEmail().toLowerCase() + "%"));
        }

        if (customerModel.getPhoneNumber() != null && customerModel.getPhoneNumber().length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("phone_number")), "%" + customerModel.getPhoneNumber().toLowerCase() + "%"));
        }

        cq.orderBy(cb.asc(root.get("id")));
        return p;
    }
}
