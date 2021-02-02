package com.codegym.service.implement.service;

import com.codegym.entity.service.ServiceType;
import com.codegym.repository.service.ServiceTypeRepository;
import com.codegym.service.service.ServiceTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTypeDaoImpl implements ServiceTypeDao {

    @Autowired
    ServiceTypeRepository serviceTypeRepository;


    @Override
    public List<ServiceType> findAll() {
        return serviceTypeRepository.findAll();
    }

    @Override
    public ServiceType findById(Long idServiceType) {
        return serviceTypeRepository.findById(idServiceType).orElse(null);
    }
}
