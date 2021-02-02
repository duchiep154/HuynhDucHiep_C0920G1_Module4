package com.codegym.entity.service;

import com.codegym.entity.TypeCommon;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "service_type")
public class ServiceType extends TypeCommon {

    @OneToMany(mappedBy = "serviceType", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Service> serviceSet;

    public ServiceType() {
    }

    public Set<Service> getServiceSet() {
        return serviceSet;
    }

    public void setServiceSet(Set<Service> serviceSet) {
        this.serviceSet = serviceSet;
    }
}
