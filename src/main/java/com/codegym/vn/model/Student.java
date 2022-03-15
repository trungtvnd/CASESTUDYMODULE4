package com.codegym.vn.model;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private Date birth;
    private String gender;
    private String phone;
    private String identify;
    private String address;
    private String status;
    private String picture;


    @ManyToOne
    private Courses idCourse;
    @OneToOne
    private Account idAccount;

    @OneToOne
    private Classes idClasses;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
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

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Courses getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Courses idCourse) {
        this.idCourse = idCourse;
    }

    public Account getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Account idAccount) {
        this.idAccount = idAccount;
    }

    public Classes getIdClasses() {
        return idClasses;
    }

    public void setIdClasses(Classes idClasses) {
        this.idClasses = idClasses;
    }
}
