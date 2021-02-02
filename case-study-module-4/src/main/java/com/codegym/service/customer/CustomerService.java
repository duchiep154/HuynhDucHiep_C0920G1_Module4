package com.codegym.service.customer;

import com.codegym.entity.customer.Customer;
import com.codegym.entity.customer.CustomerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    Customer findByIdCard(String idCard);

    Customer findByPhoneNumber(String phone);

    Customer findByEmail(String email);

    void save(Customer customer);

    Customer findByCode(String code);

    Page<Customer> findAll(Pageable pageable);

    Customer findById(Long idCustomer);

    Page<Customer> findAllByNameCustomer(String nameCustomer, Pageable pageable);

    Page<Customer> findAllByCustomerType(CustomerType customerType, Pageable pageable);
}
