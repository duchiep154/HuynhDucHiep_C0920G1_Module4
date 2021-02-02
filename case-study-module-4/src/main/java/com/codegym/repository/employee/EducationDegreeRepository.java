package com.codegym.repository.employee;

import com.codegym.entity.employee.EducationDegree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationDegreeRepository extends JpaRepository<EducationDegree, Long> {
}
