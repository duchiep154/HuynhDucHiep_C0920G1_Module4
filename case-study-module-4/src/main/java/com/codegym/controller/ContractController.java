package com.codegym.controller;

import com.codegym.entity.contract.Contract;
import com.codegym.entity.contract.ContractDetail;
import com.codegym.entity.service.AttachService;
import com.codegym.entity.service.Service;
import com.codegym.service.contract.ContractService;
import com.codegym.service.service.AttachServiceDao;
import com.codegym.service.service.ServiceDao;
import com.codegym.service.user.UserService;
import com.codegym.validatior.ContractValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Controller
@SessionAttributes({"serviceTypeList"})
@RequestMapping("/service")
public class ContractController {

    @Autowired
    ContractService contractService;

    @Autowired
    ServiceDao serviceDao;

    @Autowired
    AttachServiceDao attachServiceDao;

    @Autowired
    UserService userService;

    @Autowired
    ContractValidator contractValidator;

    @GetMapping("/booking/{idService}")
    public String goBookingService(@PathVariable Long idService,
                                   Model model,
                                   HttpSession session,
                                   Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        com.codegym.entity.user.User userMain = userService.findByUsername(user.getUsername());

        if (userMain.getCustomer() == null) {
            return "redirect:/customer/detail";
        }

        if (serviceDao.findById(idService) != null) {
            model.addAttribute("service", serviceDao.findById(idService));

            Contract contract = new Contract();
            contract.setService(serviceDao.findById(idService));

            String code;
            do {
                code = String.valueOf(new Random().nextInt(9999-1000)+1000);
            } while (contractService.findByCode(code) != null);

            contract.setCode(code);
            contract.setStartDate(LocalDate.now().toString());
            model.addAttribute("contract", contract);
            model.addAttribute("attachServiceList",attachServiceDao.findAll());

            double result1 =0;
            double result2 =0;
            session.setAttribute("resultSession1", result1);
            session.setAttribute("resultSession2", result2);

        }

        return "view/contract/booking-service";
    }


    @PostMapping("/booking")
    public String booking(@Valid @ModelAttribute Contract contract,
                          BindingResult bindingResult,
                          Principal principal,
                          HttpSession session,
                          Model model) {

        contractValidator.validate(contract, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("attachServiceList",attachServiceDao.findAll());
            return "view/contract/booking-service";
//            return "redirect:/service/booking/"+contract.getService().getId();
        }

        HashMap<Long, AttachService> attachServiceHashMap = (HashMap<Long, AttachService>) session.getAttribute("attachServiceSession");
        User user = (User) ((Authentication ) principal) .getPrincipal();

        contract.setCustomer(userService.findByUsername(user.getUsername()).getCustomer());

        Set<ContractDetail> contractDetailSet = new HashSet<>();

        for (Long key: attachServiceHashMap.keySet()) {
            ContractDetail contractDetail = new ContractDetail();
            contractDetail.setAttachService(attachServiceHashMap.get(key));
            contractDetail.setQuantity(attachServiceHashMap.get(key).getAmount());
            contractDetail.setContract(contract);
            contractDetailSet.add(contractDetail);

            AttachService attachService = attachServiceDao.findById(key);
            attachService.setAmount(String.valueOf(Integer.parseInt(attachService.getAmount()) - Integer.parseInt(attachServiceHashMap.get(key).getAmount())));
            attachServiceDao.save(attachService);
        }
        contract.setContractDetailSet(contractDetailSet);

        contract.setStatus(false);
        contractService.save(contract);

        Service service = serviceDao.findById(contract.getService().getId());
        service.setStatus(true);

        serviceDao.save(service);

        return "redirect:/home";
    }


    @GetMapping("/history")
    public String history(Principal principal, Model model) {
        User user = (User) ((Authentication) principal).getPrincipal();

        com.codegym.entity.user.User userMain = userService.findByUsername(user.getUsername());

        if (userMain.getCustomer() != null) {
            model.addAttribute("contractList", userMain.getCustomer().getContractSet());
        } else {
            model.addAttribute("contractList");
        }

        return "view/service/history";
    }

}
