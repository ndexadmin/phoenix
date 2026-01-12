package com.ndexsystems.phoenix.services.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ndexsystems.phoenix.dto.UserDTO;
import com.ndexsystems.phoenix.dto.NdexEnumerationElement;
import com.ndexsystems.phoenix.util.Constants;

public class ProductAccessServiceTest {

    private ProductAccessService service;

    @BeforeEach
    void setup() {
        service = new ProductAccessService();
    }

    private UserDTO createUserWithType(String backOfficeId) {
        UserDTO dto = new UserDTO();
        dto.setType(new NdexEnumerationElement(
                backOfficeId,
                "usertype",
                "dummy",
                "Dummy"
        ));
        return dto;
    }

    @Test
    void test_fullservice_allowed_types() {
        UserDTO u0 = createUserWithType("0");
        UserDTO u1 = createUserWithType("1");

        assertTrue(service.isAllowedToUseProduct(u0, Constants.PRODUCT_FULLSERVICE));
        assertTrue(service.isAllowedToUseProduct(u1, Constants.PRODUCT_FULLSERVICE));
    }

    @Test
    void test_fullservice_rejected_other_types() {
        UserDTO u2 = createUserWithType("2");

        assertFalse(service.isAllowedToUseProduct(u2, Constants.PRODUCT_FULLSERVICE));
    }

    @Test
    void test_online_allowed_types() {
        assertTrue(service.isAllowedToUseProduct(createUserWithType("2"), Constants.PRODUCT_ONLINE));
        assertTrue(service.isAllowedToUseProduct(createUserWithType("OTHER"), Constants.PRODUCT_ONLINE));
        assertTrue(service.isAllowedToUseProduct(createUserWithType("DOCSH"), Constants.PRODUCT_ONLINE));
    }

    @Test
    void test_online_rejected_types() {
        assertFalse(service.isAllowedToUseProduct(createUserWithType("0"), Constants.PRODUCT_ONLINE));
        assertFalse(service.isAllowedToUseProduct(createUserWithType("1"), Constants.PRODUCT_ONLINE));
    }

    @Test
    void test_empty_product_allowed() {
        assertTrue(service.isAllowedToUseProduct(createUserWithType("0"), ""));
    }

    @Test
    void test_invalid_product_rejected() {
        assertFalse(service.isAllowedToUseProduct(createUserWithType("1"), "INVALID"));
    }
}
