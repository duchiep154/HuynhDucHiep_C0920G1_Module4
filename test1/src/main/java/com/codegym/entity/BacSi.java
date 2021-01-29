package com.codegym.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class BacSi {

    @Id
    @GeneratedValue(generator = "my_generator2")
    @GenericGenerator(name = "my_generator2", strategy = "com.codegym.common.MyGenerator2")
    @Column(name="id")
    private String id;
    private String name;
    private String phone;

    @OneToMany(mappedBy = "bacSi", cascade = CascadeType.ALL)
    private List<BenhAn> benhAnList;

    public BacSi() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<BenhAn> getBenhAnList() {
        return benhAnList;
    }

    public void setBenhAnList(List<BenhAn> benhAnList) {
        this.benhAnList = benhAnList;
    }
}
