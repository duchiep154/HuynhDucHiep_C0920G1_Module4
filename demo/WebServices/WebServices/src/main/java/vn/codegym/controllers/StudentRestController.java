package vn.codegym.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.model.Student;
import vn.codegym.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentRestController {
    @Autowired
    StudentService studentService;

    // Lay danh sach student
    @GetMapping("/student")
    public ResponseEntity<List<Student>> getStudentList(){
        List<Student> studentList = studentService.findAll();
        if(studentList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    // them mot student vao danh sach
    @PostMapping("/student")
    public ResponseEntity<List<Student>> addStudent(@RequestBody Student student){
        studentService.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Tim kiem mot student
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable int id){
        Student student = studentService.findById(id);
        if(student==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    // update mot student
    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student){
        Student currentStudent = studentService.findById(id);
        if(currentStudent==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentStudent.setName(student.getName());
        currentStudent.setGender(student.getGender());

        studentService.save(currentStudent);

        return new ResponseEntity<>(currentStudent, HttpStatus.OK);
    }

    // xoa mot student
    @DeleteMapping("/student/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id){
        Student currentStudent = studentService.findById(id);
        if(currentStudent==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.delete(id);
        return new ResponseEntity<>(currentStudent, HttpStatus.OK);
    }

}
