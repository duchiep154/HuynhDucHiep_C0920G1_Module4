package com.codegym.service.implement.employee;

import com.codegym.entity.employee.Position;
import com.codegym.repository.employee.PositionRepository;
import com.codegym.service.employee.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionRepository positionRepository;

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public Position findById(Long posId) {
        return positionRepository.findById(posId).orElse(null);
    }
}
