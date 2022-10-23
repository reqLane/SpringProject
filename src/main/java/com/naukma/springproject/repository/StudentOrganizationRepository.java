package com.naukma.springproject.repository;

import com.naukma.springproject.entity.StudentOrganization;
import com.naukma.springproject.entity.key.StudentOrganizationKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentOrganizationRepository extends CrudRepository<StudentOrganization, StudentOrganizationKey> {
}
