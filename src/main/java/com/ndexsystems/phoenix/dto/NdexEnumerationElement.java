package com.ndexsystems.phoenix.dto;

import java.io.Serializable;

public record NdexEnumerationElement(
        String backOfficeId,
        String ndexEnumerationName,
        String resourceKey,
        String value
) implements Serializable {}
