package com.codegym.validatior;

import com.codegym.entity.service.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

public class ServiceValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Service service = (Service) target;

        boolean flag = true;

        // Check name
        if (!Pattern.compile("^[A-Z][a-z]+(\\s[A-Z][a-z]+){0,9}$").matcher(service.getName()).find()) {
            errors.rejectValue("name","service.name.format");
        }


        // Check Area
        try {
        Double.parseDouble(service.getArea());
        } catch (NumberFormatException e) {
            flag = false;
        }

        if (!flag) {
            errors.rejectValue("area","service.area.format");
        } else if (Double.parseDouble(service.getArea()) <= 40) {
            errors.rejectValue("area","service.area.value");
        }

        flag = true;


        // Check Cost
        try {
            Double.parseDouble(service.getCost());
        } catch (NumberFormatException e) {
            flag = false;
        }

        if (!flag) {
            errors.rejectValue("cost","service.cost.format");
        } else if (Double.parseDouble(service.getCost()) <= 0) {
            errors.rejectValue("cost","service.cost.value");
        }

        flag = true;


        // Check Max people
        try {
            Integer.parseInt(service.getMaxPeople());
        } catch (NumberFormatException e) {
            flag = false;
        }

        if (!flag) {
            errors.rejectValue("maxPeople", "service.maxPeople.format");
        } else if (Integer.parseInt(service.getMaxPeople()) <= 0) {
            errors.rejectValue("maxPeople", "service.maxPeople.value");
        }

        flag = true;

        if (!service.getServiceType().getName().equals("Room")) {
            // Check number of floor
            try {
                Integer.parseInt(service.getNumberOfFloor());
            } catch (NumberFormatException e) {
                flag = false;
            }

            if (!flag) {
                errors.rejectValue("numberOfFloor", "service.numberOfFloor.format");
            } else if (Integer.parseInt(service.getNumberOfFloor()) <= 0 ) {
                errors.rejectValue("numberOfFloor", "service.numberOfFloor.value");
            }

            flag = true;


            // Check pool Area
            try {
                Double.parseDouble(service.getPoolArea());
            } catch (NumberFormatException e) {
                flag = false;
            }

            if (!flag) {
                errors.rejectValue("poolArea","service.poolArea.format");
            } else if (Double.parseDouble(service.getPoolArea()) <= 20) {
                errors.rejectValue("poolArea","service.poolArea.value");
            }
        } else {
            service.setNumberOfFloor(null);
            service.setPoolArea(null);
        }



    }
}
