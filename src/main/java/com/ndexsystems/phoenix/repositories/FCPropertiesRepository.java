package com.ndexsystems.phoenix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.ndexsystems.phoenix.entities.FCProperties;

@Repository
public interface FCPropertiesRepository extends JpaRepository<FCProperties, String> {

    @Procedure(procedureName = "sps_FirmProperties")
    List<FCProperties> loadFirmProperties();
}
