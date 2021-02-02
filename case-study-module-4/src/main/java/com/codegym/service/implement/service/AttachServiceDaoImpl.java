package com.codegym.service.implement.service;

import com.codegym.entity.service.AttachService;
import com.codegym.repository.service.AttachServiceRepository;
import com.codegym.service.service.AttachServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachServiceDaoImpl implements AttachServiceDao {

    @Autowired
    AttachServiceRepository attachServiceRepository;

    @Override
    public AttachService findById(Long idAttachService) {
        return attachServiceRepository.findById(idAttachService).orElse(null);
    }

    @Override
    public List<AttachService> findAll() {
        return attachServiceRepository.findAll();
    }

    @Override
    public void save(AttachService attachService) {
        attachServiceRepository.save(attachService);
    }
}
