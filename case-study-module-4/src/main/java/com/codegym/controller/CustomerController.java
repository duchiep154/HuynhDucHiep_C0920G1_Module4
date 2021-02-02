package com.codegym.controller;

import com.codegym.entity.customer.Customer;
import com.codegym.service.customer.CustomerService;
import com.codegym.service.user.UserService;
import com.codegym.validatior.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Random;

@Controller
@SessionAttributes({"serviceTypeList"})
@RequestMapping({"/customer"})
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    UserService userService;

    @Autowired
    CustomerValidator customerValidator;


    @GetMapping("/detail")
    public String goDetail(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        com.codegym.entity.user.User mainUser = userService.findByUsername(user.getUsername());

        Customer customer = mainUser.getCustomer();

        if (customer == null) {

            customer = new Customer();

            String code;
            do {
                code = String.valueOf(new Random().nextInt(9999-1000)+1000);
            } while (customerService.findByCode(code) != null);

            customer.setUser(mainUser);
            customer.setCode(code);

        }

        model.addAttribute("customer", customer);


        return "view/customer/detail";
    }


    @PostMapping("/save")
    public String saveCustomer(@Valid @ModelAttribute Customer customer,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        customerValidator.validate(customer, bindingResult);

        if (bindingResult.hasErrors()) {
            return "view/customer/detail";
        }

        customerService.save(customer);
        redirectAttributes.addFlashAttribute("success","Save successfully");
        return "redirect:/customer/detail";
    }
}
