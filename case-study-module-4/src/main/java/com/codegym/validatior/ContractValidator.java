package com.codegym.validatior;

import com.codegym.entity.contract.Contract;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class ContractValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Contract contract = (Contract) target;

        LocalDate startDate = LocalDate.parse(contract.getStartDate());
        LocalDate endDate = LocalDate.parse(contract.getEndDate());

        if (endDate.compareTo(startDate) <= 0) {
            errors.rejectValue("endDate","contract.endDate.error");
        }
    }
}
