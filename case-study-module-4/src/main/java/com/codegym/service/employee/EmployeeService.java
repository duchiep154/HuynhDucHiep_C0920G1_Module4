package com.codegym.service.employee;

import com.codegym.entity.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    Page<Employee> findAll(Pageable pageable);

    List<Employee> findAll();

    Employee findByCode(String code);

    Employee findByEmail(String email);

    Employee findByIdCard(String idCard);

    Employee findByPhoneNumber(String phone);

    void save(Employee employee);

    Employee findById(Long epId);

    void deleteById(Long id);
}
