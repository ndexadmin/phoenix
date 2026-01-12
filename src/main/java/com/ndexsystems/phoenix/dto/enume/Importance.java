package com.ndexsystems.phoenix.dto.enume;

import java.util.Arrays;

public enum Importance {

    INFO("0", "Information", "Information"),
    WARNING("1", "Warning", "Avertissement"),
    ERROR("2", "Error", "Erreur");

    private final String persistentId;
    private final String labelEn;
    private final String labelFr;

    Importance(String persistentId, String labelEn, String labelFr) {
        this.persistentId = persistentId;
        this.labelEn = labelEn;
        this.labelFr = labelFr;
    }

    public String getPersistentId() {
        return persistentId;
    }

    public String getLabel(String locale) {
        if ("fr".equalsIgnoreCase(locale)) {
            return labelFr;
        }
        return labelEn;
    }

    public static Importance fromPersistentId(String id) {
        return Arrays.stream(values())
                .filter(i -> i.persistentId.equals(id))
                .findFirst()
                .orElse(INFO); // fallback legacy safe
    }
}