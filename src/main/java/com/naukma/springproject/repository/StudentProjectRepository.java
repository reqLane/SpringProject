package com.naukma.springproject.repository;

import com.naukma.springproject.entity.StudentProject;
import com.naukma.springproject.entity.key.StudentProjectKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentProjectRepository extends CrudRepository<StudentProject, StudentProjectKey> {
}
