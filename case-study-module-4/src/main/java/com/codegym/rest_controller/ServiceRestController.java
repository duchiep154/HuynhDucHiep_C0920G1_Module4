package com.codegym.rest_controller;


import com.codegym.entity.service.Service;
import com.codegym.service.service.ServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-service")
public class ServiceRestController {

    @Autowired
    private ServiceDao serviceDao;


    @GetMapping("/detail/{id}")
    public ResponseEntity<Service> returnService(@PathVariable Long id) {
        Service service = serviceDao.findById(id);

        return new ResponseEntity<>(service,HttpStatus.OK);
    }
}
