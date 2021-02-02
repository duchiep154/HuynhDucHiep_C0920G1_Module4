package com.codegym.repository.contract;

import com.codegym.entity.contract.ContractDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractDetailRepository extends JpaRepository<ContractDetail, Long> {
}
