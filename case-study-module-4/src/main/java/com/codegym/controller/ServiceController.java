package com.codegym.controller;

import com.codegym.entity.customer.Customer;
import com.codegym.entity.service.Service;
import com.codegym.entity.service.ServiceType;
import com.codegym.service.service.RentTypeDao;
import com.codegym.service.service.ServiceDao;
import com.codegym.service.service.ServiceTypeDao;
import com.codegym.service.user.UserService;
import com.codegym.validatior.ServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping({"/service"})
public class ServiceController {


    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private ServiceTypeDao serviceTypeDao;

    @Autowired
    private RentTypeDao rentTypeDao;

    @Autowired
    private UserService userService;

    @GetMapping({"", "/list"})
    public String goList(@PageableDefault(size = 3) Pageable pageable,
                         Model model,
                         @RequestParam(defaultValue = "") String search,
                         @RequestParam(defaultValue = "") String idServiceType) {

        if (!search.equals("")) {
            model.addAttribute("search", search);
            model.addAttribute("serviceList", serviceDao.findByName(search, pageable));
        } else if (!idServiceType.equals("")) {
            model.addAttribute("idServiceType", idServiceType);
            model.addAttribute("serviceList", serviceDao.findByServiceType(serviceTypeDao.findById(Long.parseLong(idServiceType)), pageable));
        }else {
            model.addAttribute("serviceList", serviceDao.findAll(pageable));
        }

        return "view/service/list";
    }

    @GetMapping("/detail/{id}")
    public String goDetail(@PathVariable Long id, Model model) {

//        -----------------
        if (serviceDao.findById(id) == null) {
            return "404";
        }

        model.addAttribute("service", serviceDao.findById(id));
        return "view/service/detail";
    }


    @GetMapping("/register")
    public String goRegisterService(Model model) {

        Service service = new Service();

        String code;
        do {
            code = String.valueOf(new Random().nextInt(9999 - 1000) + 1000);
        } while (serviceDao.findByCode(code) != null);


        service.setCode(code);
        model.addAttribute("service", service);
        model.addAttribute("rentTypeList", rentTypeDao.findAll());
        model.addAttribute("serviceTypeList", serviceTypeDao.findAll());
        return "view/service/register";
    }


    @PostMapping("/register")
    public String registerService(@Valid @ModelAttribute Service service,
                                  BindingResult bindingResult,
                                  Model model,
                                  Principal principal) {

        new ServiceValidator().validate(service, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("rentTypeList", rentTypeDao.findAll());
            model.addAttribute("serviceTypeList", serviceTypeDao.findAll());
            return "view/service/register";
        }

        User userFake = (User) ((Authentication) principal).getPrincipal();

        com.codegym.entity.user.User userMain = userService.findByUsername(userFake.getUsername());

        service.setUser(userMain);

        if (service.getId() == null) {
            service.setStatus(false);
        }


        serviceDao.save(service);


        return "redirect:/home";
    }



    @GetMapping("/my-service")
    public String myService(Principal principal,
                            Model model,
                            @PageableDefault(size = 5) Pageable pageable) {

        User user = (User) ((Authentication) principal).getPrincipal();

        com.codegym.entity.user.User userMain = userService.findByUsername(user.getUsername());

        model.addAttribute("serviceList", serviceDao.findAllByUser(userMain, pageable));

        return "view/service/my-service";
    }


    @GetMapping("/update/{id}")
    public String updateService(@PathVariable Long id, Model model) {

        Service service = serviceDao.findById(id);

        model.addAttribute("service",service);
        model.addAttribute("rentTypeList", rentTypeDao.findAll());
        model.addAttribute("serviceTypeList", serviceTypeDao.findAll());

        return "view/service/update";
    }

}
