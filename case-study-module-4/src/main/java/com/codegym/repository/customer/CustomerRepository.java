package com.codegym.repository.customer;

import com.codegym.entity.customer.Customer;
import com.codegym.entity.customer.CustomerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByIdCard(String idCard);

    Customer findByPhone(String phoneNumber);

    Customer findByEmail(String email);

    Customer findByCode(String code);

    List<Customer> findAllByNameOrCode(String name, String code);

    Page<Customer> findAllByNameContaining(String name, Pageable pageable);

    Page<Customer> findAllByCustomerType(CustomerType customerType, Pageable pageable);
}
