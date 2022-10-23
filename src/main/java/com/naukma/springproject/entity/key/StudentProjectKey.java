package com.naukma.springproject.entity.key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentProjectKey implements Serializable {

    @Column(name = "student_id")
    Long studentId;
    @Column(name = "project_id")
    Long projectId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentProjectKey that = (StudentProjectKey) o;
        return studentId.equals(that.studentId) && projectId.equals(that.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, projectId);
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
