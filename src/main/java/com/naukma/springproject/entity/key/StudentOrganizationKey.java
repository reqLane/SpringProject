package com.naukma.springproject.entity.key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentOrganizationKey implements Serializable {

    @Column(name = "student_id")
    Long studentId;
    @Column(name = "organization_id")
    Long organizationId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentOrganizationKey that = (StudentOrganizationKey) o;
        return studentId.equals(that.studentId) && organizationId.equals(that.organizationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, organizationId);
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}
