package com.codegym.service.contract;

import com.codegym.entity.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContractService {
    Contract findByCode(String code);

    void save(Contract contract);

    Page<Contract> findAll(Pageable pageable);

    Contract findById(Long idContract);
}
