package com.codegym.validatior;

import com.codegym.entity.customer.Customer;
import com.codegym.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Component
public class CustomerValidator implements Validator {

    @Autowired
    CustomerService customerService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;
        LocalDate dateOfBirth = LocalDate.parse(customer.getDateOfBirth());


        if (customer.getId() == null) {
            if (customerService.findByEmail(customer.getEmail()) != null) {
                errors.rejectValue("email","customer.email.duplicate");
            }

            if (customerService.findByIdCard(customer.getIdCard()) != null) {
                errors.rejectValue("idCard","customer.idCard.duplicate");
            }

            if (customerService.findByPhoneNumber(customer.getPhone()) != null) {
                errors.rejectValue("phone","customer.phone.duplicate");
            }

        } else {
            Customer customerCheck = customerService.findById(customer.getId());

            if (!customer.getEmail().equals(customerCheck.getEmail())) {
                if (customerService.findByEmail(customer.getEmail()) != null) {
                    errors.rejectValue("email","customer.email.duplicate");
                }
            }

            if (!customer.getIdCard().equals(customerCheck.getIdCard())) {
                if (customerService.findByIdCard(customer.getIdCard()) != null) {
                    errors.rejectValue("idCard","customer.idCard.duplicate");
                }
            }

            if (!customer.getPhone().equals(customerCheck.getPhone())) {
                if (customerService.findByPhoneNumber(customer.getPhone()) != null) {
                    errors.rejectValue("phone","customer.phone.duplicate");
                }
            }
        }



        // Check Name
        if (!Pattern.compile("^[A-Z][a-z]+(\\s[A-Z][a-z]+){0,3}$").matcher(customer.getName()).find()) {
            errors.rejectValue("name","customer.name.format");
        }


        // Check Age
        if (LocalDate.now().getYear() - dateOfBirth.getYear() < 18) {
            errors.rejectValue("dateOfBirth","customer.dateOfBirth.age");
        }


        // Check Address
        if (customer.getAddress().equals("")) {
            errors.rejectValue("address","customer.address.format");
        }


        // Check Email

        if (!Pattern.compile("^\\w{5,}.?\\w+(@\\w{3,8})(.\\w{3,8})+$").matcher(customer.getEmail()).find()) {
            errors.rejectValue("email","customer.email.format");
        }

        if (customer.getEmail().length() < 6 || customer.getEmail().length() > 32) {
            errors.rejectValue("email","customer.email.size");
        }


        // Check ID Card


        if (!Pattern.compile("^\\d{12}$").matcher(customer.getIdCard()).find()) {
            errors.rejectValue("idCard","customer.idCard.format");
        }


        // Check Phone Number


        if (!Pattern.compile("^0\\d{9}$").matcher(customer.getPhone()).find()) {
            errors.rejectValue("phone","customer.phone.format");
        }

    }
}
