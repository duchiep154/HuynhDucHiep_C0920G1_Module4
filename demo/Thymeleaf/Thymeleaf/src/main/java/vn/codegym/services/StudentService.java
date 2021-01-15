package vn.codegym.services;

import vn.codegym.model.Student;

import java.util.List;


public interface StudentService {
    List<Student> findAll();
    void save(Student student);

    Student findById(int id);
}
