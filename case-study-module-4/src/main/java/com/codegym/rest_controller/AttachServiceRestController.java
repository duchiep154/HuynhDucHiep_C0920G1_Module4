package com.codegym.rest_controller;


import com.codegym.entity.service.AttachService;
import com.codegym.service.service.AttachServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-attach-service")
public class AttachServiceRestController {

    @Autowired
    private AttachServiceDao attachServiceDao;


    @GetMapping("/update/{id}/{amount}")
    public ResponseEntity<AttachService> returnAttachService(@PathVariable Long id,
                                                             @PathVariable Integer amount) {

            AttachService attachService =  attachServiceDao.findById(id);

            attachService.setAmount(String.valueOf(Integer.parseInt(attachService.getAmount()) + amount));

            attachServiceDao.save(attachService);

            return new ResponseEntity<>(attachService, HttpStatus.OK);
    }
}
