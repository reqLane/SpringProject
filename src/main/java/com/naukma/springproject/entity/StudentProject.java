package com.naukma.springproject.entity;

import com.naukma.springproject.entity.key.StudentProjectKey;

import javax.persistence.*;

@Entity
@Table(name = "STUDENT_PROJECT")
public class StudentProject {

    @EmbeddedId
    StudentProjectKey id = new StudentProjectKey();

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    UserEntity student;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    ProjectEntity project;

    Long hours;


    public StudentProjectKey getId() {
        return id;
    }

    public void setId(StudentProjectKey id) {
        this.id = id;
    }

    public UserEntity getStudent() {
        return student;
    }

    public void setStudent(UserEntity student) {
        this.student = student;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public Long getHours() {
        if(hours == null) return 0L;
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }
}
