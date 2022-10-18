package com.naukma.springproject.repository;

import com.naukma.springproject.entity.StudentProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentProjectRepository extends CrudRepository<StudentProjectEntity, Long> {
}
