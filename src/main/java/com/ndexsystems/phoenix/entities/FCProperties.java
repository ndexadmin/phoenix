package com.ndexsystems.phoenix.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "FCProperties")
public class FCProperties {

   
    private String persistentId;
    @Id
    private String name;

    private boolean isBooleanOnly;

    private Boolean booleanValue;

    private String stringValue;

    public FCProperties() {}
}
