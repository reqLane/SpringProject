package com.naukma.springproject.entity;

import com.naukma.springproject.entity.key.StudentProjectKey;

import javax.persistence.*;

@Entity
@Table(name = "STUDENT_PROJECT")
public class StudentProject {

    @EmbeddedId
    StudentProjectKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    StudentEntity student;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    ProjectEntity project;

    int hours;


    public StudentProjectKey getId() {
        return id;
    }

    public void setId(StudentProjectKey id) {
        this.id = id;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
