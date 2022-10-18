package com.naukma.springproject.repository;

import com.naukma.springproject.entity.StudentOrganizationAdminEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentOrganizationAdminRepository extends CrudRepository<StudentOrganizationAdminEntity, Long> {
}
