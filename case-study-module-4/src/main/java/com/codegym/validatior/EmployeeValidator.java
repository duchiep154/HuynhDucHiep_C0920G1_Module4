package com.codegym.validatior;

import com.codegym.entity.employee.Employee;
import com.codegym.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Component
public class EmployeeValidator implements Validator {

    @Autowired
    EmployeeService employeeService;


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;

        LocalDate dateOfBirth = LocalDate.parse(employee.getDateOfBirth());

        if (employee.getId() == null) {
            if (employeeService.findByEmail(employee.getEmail()) != null) {
                errors.rejectValue("email","employee.email.duplicate");
            }

            if (employeeService.findByIdCard(employee.getIdCard()) != null) {
                errors.rejectValue("idCard","employee.idCard.duplicate");
            }

            if (employeeService.findByPhoneNumber(employee.getPhone()) != null) {
                errors.rejectValue("phone","employee.phone.duplicate");
            }

        }

        // Check Name
        if (!Pattern.compile("^[A-Z][a-z]+(\\s[A-Z][a-z]+){0,3}$").matcher(employee.getName()).find()) {
            errors.rejectValue("name","employee.name.format");
        }


        // Check Age
        if (LocalDate.now().getYear() - dateOfBirth.getYear() < 18) {
            errors.rejectValue("dateOfBirth","employee.dateOfBirth.age");
        }


        // Check Address
        if (employee.getAddress().equals("")) {
            errors.rejectValue("address","employee.address.format");
        }


        // Check Email

        if (!Pattern.compile("^\\w{5,}.?\\w+(@\\w{3,8})(.\\w{3,8})+$").matcher(employee.getEmail()).find()) {
            errors.rejectValue("email","employee.email.format");
        }

        if (employee.getEmail().length() < 6 || employee.getEmail().length() > 32) {
            errors.rejectValue("email","employee.email.size");
        }


        // Check ID Card


        if (!Pattern.compile("^\\d{12}$").matcher(employee.getIdCard()).find()) {
            errors.rejectValue("idCard","employee.idCard.format");
        }


        // Check Phone Number


        if (!Pattern.compile("^0\\d{9}$").matcher(employee.getPhone()).find()) {
            errors.rejectValue("phone","employee.phone.format");
        }
    }
}
