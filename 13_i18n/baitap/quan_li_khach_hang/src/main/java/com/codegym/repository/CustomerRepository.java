package com.codegym.repository;

import com.codegym.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {

    Page<Customer> findAll(Pageable pageable);


    @Query("SELECT c FROM Customer c WHERE c.typeCustomer.name LIKE %?1%"
            + " OR c.province.name LIKE %?1%"
            + "OR c.email LIKE %?1% "
            + "OR c.name LIKE %?1% "

            + " OR CONCAT(c.phone, '') LIKE %?1%")
    Page<Customer> search(Pageable pageble , String keyword);
}
