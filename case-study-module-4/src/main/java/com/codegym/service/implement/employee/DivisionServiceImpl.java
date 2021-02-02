package com.codegym.service.implement.employee;

import com.codegym.entity.employee.Division;
import com.codegym.repository.employee.DivisionRepository;
import com.codegym.service.employee.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    DivisionRepository divisionRepository;


    @Override
    public List<Division> findAll() {
        return divisionRepository.findAll();
    }

    @Override
    public Division findById(Long divId) {
        return divisionRepository.findById(divId).orElse(null);
    }
}
