package com.naukma.springproject.repository;

import com.naukma.springproject.entity.HourRequestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HourRequestRepository extends CrudRepository<HourRequestEntity, Long> {

    List<HourRequestEntity> findAll();
}
