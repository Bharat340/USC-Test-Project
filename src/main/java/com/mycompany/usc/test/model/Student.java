package com.mycompany.usc.test.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author pbharat
 */
@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue
    @Column
    private Integer studentId;

    @Column(length = 60)
    private String name;

    @Column
    private String email;

    @Column(length = 20)
    private String phoneNumber;

    @Column(columnDefinition = "tinyint(1) DEFAULT 0")
    private Boolean isDomestic;

    @Column(columnDefinition = "int(11) NOT NULL DEFAULT 0")
    private Integer departmentId;
    
    public Student() {
    }
    
    public Student(String name, String email, String phoneNumber, Boolean isDomestic, Integer departmentId) {
        this.name = name;
        this.email = email;
        this.isDomestic = isDomestic;
        this.phoneNumber = phoneNumber;
        this.departmentId = departmentId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getIsDomestic() {
        return isDomestic;
    }

    public void setIsDomestic(Boolean isDomestic) {
        this.isDomestic = isDomestic;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

}
