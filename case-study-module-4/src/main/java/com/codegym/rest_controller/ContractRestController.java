package com.codegym.rest_controller;


import com.codegym.entity.contract.Contract;
import com.codegym.entity.service.AttachService;
import com.codegym.service.service.AttachServiceDao;
import com.codegym.service.service.ServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashMap;

@RestController
@RequestMapping("/api-service")
public class ContractRestController {

    @Autowired
    ServiceDao serviceDao;

    @Autowired
    AttachServiceDao attachServiceDao;



    @GetMapping("/booking/{idService}/{startDate}/{endDate}")
    public ResponseEntity<Double> calTotalMoneyByDay(@PathVariable Long idService,
                                                   @PathVariable String startDate,
                                                   @PathVariable String endDate,
                                                   @ModelAttribute Contract contract,
                                                   HttpSession session) {
        double result2 = (double) session.getAttribute("resultSession2");


        int date = (LocalDate.parse(endDate).getDayOfYear() - LocalDate.parse(startDate).getDayOfYear()) ;

        double result1 = date * Double.parseDouble(serviceDao.findById(idService).getCost());

        session.setAttribute("resultSession1", result1);

        return new ResponseEntity<>(result1 + result2, HttpStatus.OK);
    }

    @GetMapping("/booking/{idAttachService}/{amount}")
    public ResponseEntity<Double> calTotalMoneyByAttachService(@PathVariable Long idAttachService,
                                                                @PathVariable String amount,
                                                                HttpSession session){
        HashMap<Long, AttachService> attachServiceHashMap = (HashMap<Long, AttachService>) session.getAttribute("attachServiceSession");

        double result1 = (double) session.getAttribute("resultSession1");

        if (attachServiceHashMap == null) {
            attachServiceHashMap = new HashMap<>();
        }

        AttachService attachService = attachServiceDao.findById(idAttachService);

        if (attachService != null) {
            attachService.setAmount(amount);
            attachServiceHashMap.put(idAttachService, attachService);
        }

        double result2 = 0;
        for (Long key : attachServiceHashMap.keySet()) {
            result2 += Double.parseDouble(attachServiceHashMap.get(key).getAmount()) * Double.parseDouble(attachServiceHashMap.get(key).getCost());
        }

        session.setAttribute("attachServiceSession", attachServiceHashMap);
        session.setAttribute("resultSession2", result2);

        return new ResponseEntity<>(result1+ result2,HttpStatus.OK);
    }



}
