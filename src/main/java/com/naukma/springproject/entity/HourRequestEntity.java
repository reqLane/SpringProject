package com.naukma.springproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HourRequestEntity implements Comparable<HourRequestEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentLogin;
    private String projectName;
    private Long hoursAmount;

    public HourRequestEntity(String studentLogin, String projectName, Long hoursAmount) {
        this.studentLogin = studentLogin;
        this.projectName = projectName;
        this.hoursAmount = hoursAmount;
    }
    public HourRequestEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentLogin() {
        return studentLogin;
    }

    public void setStudentLogin(String studentLogin) {
        this.studentLogin = studentLogin;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getHoursAmount() {
        return hoursAmount;
    }

    public void setHoursAmount(Long hoursAmount) {
        this.hoursAmount = hoursAmount;
    }

    @Override
    public int compareTo(HourRequestEntity o) {
        return Long.compare(this.id, o.id);
    }
}
