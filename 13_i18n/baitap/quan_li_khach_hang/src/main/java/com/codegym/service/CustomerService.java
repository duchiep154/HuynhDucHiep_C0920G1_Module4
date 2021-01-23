package com.codegym.service;

import com.codegym.entity.Customer;
import com.codegym.entity.Province;
import com.codegym.entity.TypeCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(String id);

    void save(Customer customer);

    void update(Customer customer);

    void remove(String id);

    List<TypeCustomer> findAllTypeCustomer();

    List<Province> findAllProvince();

    Page<Customer> findAll(Pageable pageable);
    Page<Customer> listAll(int pageNum, Optional<String> keyword);



}
