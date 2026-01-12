package com.ndexsystems.phoenix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ndexsystems.phoenix.entities.FCProperties;
import com.ndexsystems.phoenix.repositories.FCPropertiesRepository;

@Service
public class FCPropertiesService {

    @Autowired
    private FCPropertiesRepository repository;
    @Transactional
    public List<FCProperties> getProperties() {
        return repository.loadFirmProperties();
    }
}
