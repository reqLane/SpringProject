package com.naukma.springproject.repository;

import com.naukma.springproject.entity.StudentOrganizationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentOrganizationRepository extends CrudRepository<StudentOrganizationEntity, Long> {
}
