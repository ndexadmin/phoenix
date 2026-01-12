package com.ndexsystems.phoenix.dto;


import java.util.*;

public final class NdexEnumerations {

    private final Map<String, NdexEnumeration> enums = new HashMap<>();

    public NdexEnumeration get(String name) {
        return enums.get(name.toLowerCase());
    }

    public Collection<NdexEnumeration> all() {
        return enums.values();
    }

    public static NdexEnumerations load(ResourceBundle bundle) {

        NdexEnumerations ndex = new NdexEnumerations();

        Map<String, Map<String, String>> grouped = new HashMap<>();

      
        for (String key : bundle.keySet()) {

            String value = bundle.getString(key);
            String[] parts = key.split("\\.");
            
            String enumName = parts[0];

            grouped.computeIfAbsent(enumName, k -> new HashMap<>())
                    .put(key, value);
        }

        grouped.forEach((name, entries) -> {
            NdexEnumeration enumeration = NdexEnumeration.fromProperties(name, entries);
            ndex.enums.put(name.toLowerCase(), enumeration);
        });

        return ndex;
    }
}
