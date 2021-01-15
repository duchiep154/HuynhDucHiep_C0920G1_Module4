package vn.codegym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
