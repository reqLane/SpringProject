package com.naukma.springproject.entity;

import com.naukma.springproject.entity.key.StudentOrganizationKey;
import com.naukma.springproject.enums.StudentRoles;

import javax.persistence.*;

@Entity
@Table(name = "STUDENT_ORGANIZATION")
public class StudentOrganization {

    @EmbeddedId
    StudentOrganizationKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    StudentEntity student;

    @ManyToOne
    @MapsId("organizationId")
    @JoinColumn(name = "organization_id")
    OrganizationEntity organization;

    @Enumerated(EnumType.STRING)
    StudentRoles studentRole;


    public StudentOrganizationKey getId() {
        return id;
    }

    public void setId(StudentOrganizationKey id) {
        this.id = id;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public StudentRoles getStudentRole() {
        return studentRole;
    }

    public void setStudentRole(StudentRoles studentRole) {
        this.studentRole = studentRole;
    }
}
