package com.codegym.service.implement.service;

import com.codegym.entity.service.ServiceType;
import com.codegym.entity.user.User;
import com.codegym.repository.service.ServiceRepository;
import com.codegym.service.service.ServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceDaoImpl implements ServiceDao {

    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public Page<com.codegym.entity.service.Service> findAll(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }

    @Override
    public com.codegym.entity.service.Service findById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    @Override
    public void save(com.codegym.entity.service.Service service) {
        serviceRepository.save(service);
    }

    @Override
    public com.codegym.entity.service.Service findByCode(String code) {
        return serviceRepository.findByCode(code);
    }

    @Override
    public Page<com.codegym.entity.service.Service> findByServiceType(ServiceType serviceType, Pageable pageable) {
        return serviceRepository.findAllByServiceType(serviceType, pageable);
    }

    @Override
    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public Page<com.codegym.entity.service.Service> findByName(String nameService, Pageable pageable) {
        return serviceRepository.findAllByNameContaining(nameService, pageable);
    }

    @Override
    public Page<com.codegym.entity.service.Service> findAllByUser(User userMain, Pageable pageable) {
        return serviceRepository.findAllByUser(userMain, pageable);
    }
}
