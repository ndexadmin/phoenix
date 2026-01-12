package com.ndexsystems.phoenix.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.ndexsystems.phoenix.entities.FCProperties;
import com.ndexsystems.phoenix.util.AbstractTenantDbTest;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class FCPropertiesRepositoryTest extends AbstractTenantDbTest {

    @Autowired
    private FCPropertiesRepository repository;

    @Test
    void testStoredProcedureCall() {
        List<FCProperties> props = repository.loadFirmProperties();

        assertThat(props)
                .as("La procédure n'a retourné aucune ligne")
                .isNotNull()
                .isNotEmpty();

        FCProperties p = props.get(0);

        assertThat(p.getPersistentId())
                .as("persistentId ne doit pas être null")
                .isNotNull();

        assertThat(p.getName())
                .as("name ne doit pas être null")
                .isNotNull();

        assertThat(p.isBooleanOnly())
                .as("isBooleanOnly doit avoir une valeur (true/false)")
                .isIn(true, false);

        assertThat(p.getBooleanValue())
                .as("booleanValue peut être null ou true/false — test simple")
                .isNotNull();
    }
}
