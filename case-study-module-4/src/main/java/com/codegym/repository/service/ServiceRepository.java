package com.codegym.repository.service;

import com.codegym.entity.service.Service;
import com.codegym.entity.service.ServiceType;
import com.codegym.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    Service findByCode(String code);

    Page<Service> findAllByServiceType(ServiceType serviceType, Pageable pageable);

    Page<Service> findAllByNameContaining(String name, Pageable pageable);

    Page<Service> findAllByUser(User user, Pageable pageable);
}
