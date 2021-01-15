package vn.codegym.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Laptop {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToOne(mappedBy = "laptop")
    private Student student;

    public Laptop() {
    }

    public Laptop(String name, Student student) {
        this.name = name;
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
}
