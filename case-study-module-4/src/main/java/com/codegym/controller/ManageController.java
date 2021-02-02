package com.codegym.controller;


import com.codegym.entity.contract.Contract;
import com.codegym.entity.customer.Customer;
import com.codegym.entity.customer.CustomerType;
import com.codegym.entity.employee.Employee;
import com.codegym.entity.service.Service;
import com.codegym.entity.user.Role;
import com.codegym.entity.user.User;
import com.codegym.entity.user.UserRole;
import com.codegym.service.contract.ContractService;
import com.codegym.service.customer.CustomerService;
import com.codegym.service.customer.CustomerTypeService;
import com.codegym.service.employee.DivisionService;
import com.codegym.service.employee.EducationDegreeService;
import com.codegym.service.employee.EmployeeService;
import com.codegym.service.employee.PositionService;
import com.codegym.service.service.AttachServiceDao;
import com.codegym.service.service.ServiceDao;
import com.codegym.service.user.RoleService;
import com.codegym.service.user.UserRoleService;
import com.codegym.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@SessionAttributes({"serviceTypeList"})
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    ServiceDao serviceDao;

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ContractService contractService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    AttachServiceDao attachServiceDao;

    @Autowired
    PositionService positionService;

    @Autowired
    EducationDegreeService educationDegreeService;

    @Autowired
    DivisionService divisionService;

    @Autowired
    CustomerTypeService customerTypeService;

    @GetMapping("/service/list")
    public String goListService(Model model, @PageableDefault(size = 5)Pageable pageable) {
        model.addAttribute("serviceList",serviceDao.findAll(pageable));
        return "view/manage/service-list";
    }



    @GetMapping("/customer/list")
    public String goListCustomer(Model model,
                                 @PageableDefault(size = 5)Pageable pageable,
                                 @RequestParam(defaultValue = "") String nameCustomer,
                                 @RequestParam(defaultValue = "") String idCustomerType) {

        if (!nameCustomer.equals("")) {
            model.addAttribute("nameCustomer", nameCustomer);
            model.addAttribute("customerList", customerService.findAllByNameCustomer(nameCustomer, pageable));
        } else if (!idCustomerType.equals("")) {
            model.addAttribute("idCustomerType", idCustomerType);
            model.addAttribute("customerList", customerService.findAllByCustomerType(customerTypeService.findById(Long.parseLong(idCustomerType)),pageable));
        }else {
            model.addAttribute("customerList",customerService.findAll(pageable));
        }

        model.addAttribute("customerTypeList", customerTypeService.findAll());
        return "view/manage/customer-list";
    }


    @GetMapping("/employee/list")
    public String goListEmployee(Model model, @PageableDefault(size = 5)Pageable pageable) {
        model.addAttribute("employeeList",employeeService.findAll(pageable));
        model.addAttribute("positionList",positionService.findAll());
        model.addAttribute("educationDegreeList",educationDegreeService.findAll());
        model.addAttribute("divisionList",divisionService.findAll());

        return "view/manage/employee-list";
    }


    @GetMapping("/contract/list")
    public String goListContract(Model model, @PageableDefault(size = 5)Pageable pageable) {
        model.addAttribute("employeeList", employeeService.findAll());
        model.addAttribute("contractList",contractService.findAll(pageable));
        return "view/manage/contract-list";
    }


    @GetMapping("/user/list")
    public String goListUser(Model model, @PageableDefault(size = 5)Pageable pageable) {
        model.addAttribute("userList",userService.findAll(pageable));
        model.addAttribute("roleList", roleService.findAll());
        return "view/manage/user-list";
    }


    @GetMapping("/attach-service/list")
    public String goListAttachService(Model model) {
        model.addAttribute("attachServiceList",attachServiceDao.findAll());
        return "view/manage/attach-service-list";
    }


    @GetMapping("/contract/detail/{idContract}")
    public String goDetailContract(@PathVariable Long idContract, Model model) {
        model.addAttribute("contract",contractService.findById(idContract));
        return "view/manage/contract-detail";
    }


    @PostMapping("/user/update-role")
    public String updateRole(@RequestParam Long roleId,
                             @RequestParam Long userId) {

        User user = userService.findById(userId);
        Role role = roleService.findById(roleId);
        UserRole userRole = new UserRole();

        userRole.setUser(user);
        userRole.setRole(role);

        userRoleService.save(userRole);

        return "redirect:/manage/user/list";
    }


    @GetMapping("/contract/payment/{idContract}")
    public String payment(@PathVariable Long idContract, Principal principal) {
        Contract contract = contractService.findById(idContract);

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();

        User userMain = userService.findByUsername(user.getUsername());

        if (userMain.getEmployee() == null) {
            return "redirect:/employee/detail";
        }
        if (contract != null) {
            contract.setStatus(true);
            contract.setEmployee(userMain.getEmployee());
            contractService.save(contract);

            Service service = serviceDao.findById(contract.getService().getId());
            service.setStatus(false);
            serviceDao.save(service);

        }
        return "redirect:/manage/contract/list";
    }



    // Update Employeeeeee
    @PostMapping("/employee/update")
    public String updateEmployee(@RequestParam Long epId,
                                 @RequestParam Long posId,
                                 @RequestParam Long eduId,
                                 @RequestParam Long divId) {
        Employee employee = employeeService.findById(epId);

        employee.setPosition(positionService.findById(posId));
        employee.setEducationDegree(educationDegreeService.findById(eduId));
        employee.setDivision(divisionService.findById(divId));

        employeeService.save(employee);

        return "redirect:/manage/employee/list";
    }




//     Delete Service
    @GetMapping("/service/delete/{id}")
    public String deleteServiceById(@PathVariable Long id) {
        serviceDao.deleteById(id);

        return "redirect:/manage/service/list";
    }


//     Delete Service
    @GetMapping("/employee/delete/{id}")
    public String deleteEmployeeId(@PathVariable Long id) {
        employeeService.deleteById(id);

        return "redirect:/manage/employee/list";
    }



    @PostMapping("/customer/update")
    public String updateCustomerType(@RequestParam Long idCustomer,
                                     @RequestParam Long idCustomerType) {

        Customer customer = customerService.findById(idCustomer);

        CustomerType customerType = customerTypeService.findById(idCustomerType);

        customer.setCustomerType(customerType);

        customerService.save(customer);


        return "redirect:/manage/customer/list";
    }


    @PostMapping("/contract/update")
    public String updateContract(@RequestParam Long idContract,
                                 @RequestParam Long idEmployee) {

        Contract contract = contractService.findById(idContract);

        contract.setEmployee(employeeService.findById(idEmployee));

        contractService.save(contract);

        return "redirect:/manage/contract/list";
    }
}
