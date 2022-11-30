package com.naukma.springproject.entity;

import com.naukma.springproject.entity.key.StudentOrganizationKey;

import javax.persistence.*;

@Entity
@Table(name = "STUDENT_ORGANIZATION")
public class StudentOrganization {

    @EmbeddedId
    StudentOrganizationKey id = new StudentOrganizationKey();

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    UserEntity student;

    @ManyToOne
    @MapsId("organizationId")
    @JoinColumn(name = "organization_id")
    OrganizationEntity organization;


    public StudentOrganizationKey getId() {
        return id;
    }

    public void setId(StudentOrganizationKey id) {
        this.id = id;
    }

    public UserEntity getStudent() {
        return student;
    }

    public void setStudent(UserEntity student) {
        this.student = student;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }
}
