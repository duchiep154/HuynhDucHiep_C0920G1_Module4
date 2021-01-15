package vn.codegym.repository;

import org.springframework.stereotype.Repository;
import vn.codegym.model.Student;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private static List<Student> studentList;

    static {
        studentList = new ArrayList<>();
        studentList.add(new Student(1, "Nguyen Van A", 0, new String[]{"JAVA", "C#"}));
        studentList.add(new Student(2, "Nguyen Van B", 1, new String[]{"PHP", "C#"}));
        studentList.add(new Student(3, "Nguyen Van C", 0, new String[]{"C#"}));
    }

    @Override
    public List<Student> findAll() {
        return studentList;
    }

    @Override
    public void save(Student student) {
        int index = studentList.indexOf(student);
        if(index != -1){
            studentList.set(index, student);
        }else {
            studentList.add(student);
        }

    }

    @Override
    public Student findById(int id) {
        return studentList.get(id);
    }
}
