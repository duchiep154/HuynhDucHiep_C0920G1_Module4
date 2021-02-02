package com.codegym.service.implement.employee;

import com.codegym.entity.employee.Employee;
import com.codegym.repository.employee.EmployeeRepository;
import com.codegym.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findByCode(String code) {
        return employeeRepository.findByCode(code);
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public Employee findByIdCard(String idCard) {
        return employeeRepository.findByIdCard(idCard);
    }

    @Override
    public Employee findByPhoneNumber(String phone) {
        return employeeRepository.findByPhone(phone);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee findById(Long epId) {
        return employeeRepository.findById(epId).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
