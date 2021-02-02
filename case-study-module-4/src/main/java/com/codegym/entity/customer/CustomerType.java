package com.codegym.entity.customer;


import com.codegym.entity.TypeCommon;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer_type")
public class CustomerType extends TypeCommon {

    @OneToMany(mappedBy = "customerType", cascade = CascadeType.ALL)
    @JsonBackReference
    Set<Customer> customerSet;

    public CustomerType() {
    }

    public Set<Customer> getCustomerSet() {
        return customerSet;
    }

    public void setCustomerSet(Set<Customer> customerSet) {
        this.customerSet = customerSet;
    }
}
