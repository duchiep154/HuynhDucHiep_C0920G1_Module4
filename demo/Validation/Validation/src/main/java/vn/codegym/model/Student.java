package vn.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Student implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Vui lòng nhập tên.")
    @Size(min = 5, max = 10)
    private String name;
    private int gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "laptop_id", referencedColumnName = "id")
    private Laptop laptop;

    public Student(String name, int gender, Laptop laptop) {
        this.name = name;
        this.gender = gender;
        this.laptop = laptop;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private ClassRoom classRoom;

    @ManyToMany
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Student student = (Student) target;
        if("Nhan".equals(student.name) && "HP".equals(student.laptop.getName())){
            errors.rejectValue("laptop","latop.name.combined");
        }
    }
}
