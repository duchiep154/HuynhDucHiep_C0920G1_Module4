package com.codegym.service;

import com.codegym.entity.Customer;
import com.codegym.entity.Province;
import com.codegym.entity.TypeCustomer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(String id);

    void save(Customer customer);

    void update(Customer customer);

    void remove(String id);

    List<TypeCustomer> findAllTypeCustomer();

    List<Province> findAllProvince();

}
