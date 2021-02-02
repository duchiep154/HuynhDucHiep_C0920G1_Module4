package com.codegym.repository.employee;

import com.codegym.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByCode(String code);

    Employee findByEmail (String email);

    Employee findByPhone (String numberPhone);

    Employee findByIdCard(String idCard);
}
