package com.codegym.controller;

import com.codegym.entity.Customer;
import com.codegym.entity.Province;
import com.codegym.entity.TypeCustomer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping()
    public String home(Model model) {
        model.addAttribute("customerList", customerService.findAll());
        return "customer/home";


    }

    @GetMapping("/create")
    public String create(Model model) {
        List<TypeCustomer> typeCustomerList = this.customerService.findAllTypeCustomer();
        List<Province> provinceList = this.customerService.findAllProvince();
        model.addAttribute("typeCustomerList", typeCustomerList);
        model.addAttribute("provinceList", provinceList);

        model.addAttribute("newCustomer", new Customer());
        return "customer/create";

    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute(value = "newCustomer") Customer customer, BindingResult bindingResult, Model model,
                       RedirectAttributes redirect) {
        boolean check = false;
        List<Customer> customerList = this.customerService.findAll();


        if (bindingResult.hasFieldErrors() || check) {
            model.addAttribute("customerList", this.customerService.findAll());
            List<Province> provinceList = this.customerService.findAllProvince();
            model.addAttribute("provinceList", provinceList);


            List<TypeCustomer> typeCustomerList = this.customerService.findAllTypeCustomer();
            model.addAttribute("typeCustomerList", typeCustomerList);


            return "customer/create";
        }


        customerService.save(customer);
        redirect.addFlashAttribute("message", "Create Successfully");
        return "redirect:/customer";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable String id, Model model) {
        List<TypeCustomer> typeCustomerList = this.customerService.findAllTypeCustomer();
        model.addAttribute("typeCustomerList", typeCustomerList);

        List<Province> provinceList = this.customerService.findAllProvince();
        model.addAttribute("provinceList", provinceList);

        model.addAttribute("editCustomer", customerService.findById(id));
        return "customer/edit";


    }
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute(value = "editCustomer") Customer customer, BindingResult bindingResult, Model model,
                       RedirectAttributes redirect) {
        boolean check = false;
        List<Customer> customerList = this.customerService.findAll();


        if (bindingResult.hasFieldErrors() || check) {
            model.addAttribute("customerList", this.customerService.findAll());
            List<Province> provinceList = this.customerService.findAllProvince();
            model.addAttribute("provinceList", provinceList);


            List<TypeCustomer> typeCustomerList = this.customerService.findAllTypeCustomer();
            model.addAttribute("typeCustomerList", typeCustomerList);


            return "customer/edit";
        }


        customerService.update(customer);
        redirect.addFlashAttribute("message", "update Successfully");
        return "redirect:/customer";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable String id, Model model) {

        List<TypeCustomer> typeCustomerList = this.customerService.findAllTypeCustomer();
        model.addAttribute("typeCustomerList", typeCustomerList);

        List<Province> provinceList = this.customerService.findAllProvince();
        model.addAttribute("provinceList", provinceList);
        model.addAttribute("view", customerService.findById(id));
        return "customer/view";
    }

}
