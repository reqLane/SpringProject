package com.naukma.springproject.repository;

import com.naukma.springproject.entity.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {

    ProjectEntity findByName(String name);
}
