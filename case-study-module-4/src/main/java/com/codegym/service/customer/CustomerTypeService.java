package com.codegym.service.customer;

import com.codegym.entity.customer.CustomerType;

import java.util.List;

public interface CustomerTypeService {
    List<CustomerType> findAll();

    CustomerType findById(Long idCustomerType);
}
