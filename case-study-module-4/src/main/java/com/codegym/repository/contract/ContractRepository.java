package com.codegym.repository.contract;

import com.codegym.entity.contract.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    Contract findByCode(String code);
}
