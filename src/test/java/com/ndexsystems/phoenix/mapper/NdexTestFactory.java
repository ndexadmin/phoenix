package com.ndexsystems.phoenix.mapper;

import com.ndexsystems.phoenix.dto.NdexEnumeration;
import com.ndexsystems.phoenix.dto.NdexEnumerations;
import com.ndexsystems.phoenix.dto.NdexEnumerationElement;

import java.lang.reflect.Field;
import java.util.Map;

public final class NdexTestFactory {

    public static NdexEnumerations fakeEnums() {

        try {
            
            NdexEnumerationElement admin = new NdexEnumerationElement(
                    "0",          
                    "usertype",   
                    "administrator", 
                    "Administrateur" 
            );

          
            NdexEnumeration enumUserType = new NdexEnumeration("usertype");

            enumUserType.getResourceDomain().put("administrator", admin);
            enumUserType.getBackOfficeDomain().put("0", admin);

           
            NdexEnumerations enums = new NdexEnumerations();

            Field f = NdexEnumerations.class.getDeclaredField("enums");
            f.setAccessible(true);

            @SuppressWarnings("unchecked")
            Map<String, NdexEnumeration> internal =
                    (Map<String, NdexEnumeration>) f.get(enums);

            internal.put("usertype", enumUserType);

            return enums;

        } catch (Exception e) {
            throw new RuntimeException("Erreur dans NdexTestFactory", e);
        }
    }
}
