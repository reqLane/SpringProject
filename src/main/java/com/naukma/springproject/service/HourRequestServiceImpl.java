package com.naukma.springproject.service;

import com.naukma.springproject.entity.HourRequestEntity;
import com.naukma.springproject.repository.HourRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Service
public class HourRequestServiceImpl implements HourRequestService {

    private final HourRequestRepository hourRequestRepository;

    @Autowired
    public HourRequestServiceImpl(HourRequestRepository hourRequestRepository) {
        this.hourRequestRepository = hourRequestRepository;
    }

    @Override
    public void create(String studentLogin, String projectName, Long hoursAmount) {
        HourRequestEntity entity = new HourRequestEntity(studentLogin, projectName, hoursAmount);

        hourRequestRepository.save(entity);
    }

    @Override
    public List<HourRequestEntity> getAll() {
        return hourRequestRepository.findAll();
    }

    @Override
    public void delete(Long requestId) {
        hourRequestRepository.deleteById(requestId);
    }
}
