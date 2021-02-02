package com.codegym.service.implement.contract;

import com.codegym.entity.contract.Contract;
import com.codegym.repository.contract.ContractRepository;
import com.codegym.service.contract.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    ContractRepository contractRepository;

    @Override
    public Contract findByCode(String code) {
        return contractRepository.findByCode(code);
    }

    @Override
    public void save(Contract contract) {
        contractRepository.save(contract);
    }

    @Override
    public Page<Contract> findAll(Pageable pageable) {
        return contractRepository.findAll(pageable);
    }

    @Override
    public Contract findById(Long idContract) {
        return contractRepository.findById(idContract).orElse(null);
    }
}
