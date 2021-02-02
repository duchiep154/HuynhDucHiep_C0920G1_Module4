package com.codegym.entity.service;

import com.codegym.entity.TypeCommon;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "rent_type")
public class RentType extends TypeCommon {

    @OneToMany(mappedBy = "rentType", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Service> serviceSet;

    public RentType() {
    }

    public Set<Service> getServiceSet() {
        return serviceSet;
    }

    public void setServiceSet(Set<Service> serviceSet) {
        this.serviceSet = serviceSet;
    }
}
