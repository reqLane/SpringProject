package com.naukma.springproject.repository;

import com.naukma.springproject.entity.OrganizationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends CrudRepository<OrganizationEntity, Long> {
}
