package com.codegym.service.service;

import com.codegym.entity.service.ServiceType;

import java.util.List;

public interface ServiceTypeDao {
    List<ServiceType> findAll();

    ServiceType findById(Long idServiceType);
}
