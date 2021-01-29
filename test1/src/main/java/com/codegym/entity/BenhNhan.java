package com.codegym.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="benh_nhan")
public class BenhNhan {
    @Id
    @GeneratedValue(generator = "my_generator3")
    @GenericGenerator(name = "my_generator3", strategy = "com.codegym.common.MyGenerator3")
    @Column(name="id")
    private String id;
    private String name;
    private String birthday;

    private String gender;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "benhNhan", cascade = CascadeType.ALL)
    private List<BenhAn> benhAnList;

    public BenhNhan() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<BenhAn> getBenhAnList() {
        return benhAnList;
    }

    public void setBenhAnList(List<BenhAn> benhAnList) {
        this.benhAnList = benhAnList;
    }
}
