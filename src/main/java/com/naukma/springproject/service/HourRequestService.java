package com.naukma.springproject.service;

import com.naukma.springproject.entity.HourRequestEntity;

import java.util.List;

public interface HourRequestService {

    void create(String studentLogin, String projectName, Long hoursAmount);

    List<HourRequestEntity> getAll();

    void delete(Long requestId);
}
