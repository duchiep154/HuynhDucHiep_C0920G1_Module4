package com.codegym.entity.employee;

import com.codegym.entity.TypeCommon;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "position")
public class Position extends TypeCommon {

    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Employee> employeeSet;

    public Position() {
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }
}
