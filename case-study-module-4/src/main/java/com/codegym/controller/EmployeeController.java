package com.codegym.controller;

import com.codegym.entity.employee.Employee;
import com.codegym.service.employee.EmployeeService;
import com.codegym.service.user.UserService;
import com.codegym.validatior.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Random;

@Controller
@SessionAttributes({"serviceTypeList"})
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserService userService;

    @Autowired
    EmployeeValidator employeeValidator;

    @GetMapping("/detail")
    public String goDetailEmployee(Principal principal, Model model) {

        User user = (User) ((Authentication) principal).getPrincipal();

        com.codegym.entity.user.User mainUser = userService.findByUsername(user.getUsername());

        Employee employee = mainUser.getEmployee();

        if (employee == null) {
            employee = new Employee();

            String code;
            do {
                code = String.valueOf(new Random().nextInt(9999-1000)+1000);
            } while (employeeService.findByCode(code) != null);

            employee.setUser(mainUser);
            employee.setCode(code);
        }

        model.addAttribute("employee", employee);

        return "view/employee/detail";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute Employee employee,
                               BindingResult bindingResult) {

        employeeValidator.validate(employee, bindingResult);

        if (bindingResult.hasErrors()) {
            return "view/employee/detail";
        }

        employeeService.save(employee);

        return "redirect:/employee/detail";
    }
}
