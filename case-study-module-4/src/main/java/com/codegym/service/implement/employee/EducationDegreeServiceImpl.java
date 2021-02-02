package com.codegym.service.implement.employee;

import com.codegym.entity.employee.EducationDegree;
import com.codegym.repository.employee.EducationDegreeRepository;
import com.codegym.service.employee.EducationDegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationDegreeServiceImpl implements EducationDegreeService {

    @Autowired
    EducationDegreeRepository educationDegreeRepository;


    @Override
    public List<EducationDegree> findAll() {
        return educationDegreeRepository.findAll();
    }

    @Override
    public EducationDegree findById(Long eduId) {
        return educationDegreeRepository.findById(eduId).orElse(null);
    }
}
