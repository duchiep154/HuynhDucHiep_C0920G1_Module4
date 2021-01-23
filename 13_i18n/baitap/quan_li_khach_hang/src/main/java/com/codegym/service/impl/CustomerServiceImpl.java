package com.codegym.service.impl;

import com.codegym.entity.Customer;
import com.codegym.entity.Province;
import com.codegym.entity.TypeCustomer;
import com.codegym.repository.CustomerRepository;
import com.codegym.repository.ProvinceRepository;
import com.codegym.repository.TypeCustomerRepository;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TypeCustomerRepository typeCustomerRepository;

    @Autowired
    private ProvinceRepository provinceRepository;



    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findById(String id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Customer customer) {
        this.customerRepository.save(customer);

    }

    @Override
    public void update(Customer customer) {
        this.customerRepository.save(customer);

    }

    @Override
    public void remove(String id) {
        this.customerRepository.deleteById(id);

    }

    @Override
    public List<TypeCustomer> findAllTypeCustomer() {
        return this.typeCustomerRepository.findAll();
    }

    @Override
    public List<Province> findAllProvince() {
        return this.provinceRepository.findAll();
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return this.customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> listAll(int pageNum, Optional<String> keyword) {
        Pageable pageable = PageRequest.of(pageNum - 1, 5);
        String keywordOld="";

        if (!keyword.isPresent()) {
            return customerRepository.findAll(pageable);

        }else {
            keywordOld=keyword.get();
            return customerRepository.search(pageable,keywordOld);

        }
    }
}
