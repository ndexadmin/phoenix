package com.ndexsystems.phoenix.dto.enume;


public enum UserStatus {
    ACCESSALLOWED("accessAllowed", "ALOWD"),
    ACCESSNOTALLOWED("accessNotAllowed", "4BIDN"),
    DELETED("deleted", "DELET"),
    NEW("new", "NEW"),
    PASSWORDDEACTIVATED("passwordDeactivated", "PWCXL"),
    TEMPORARILYDEACTIVATED("temporarilyDeactivated", "TEMPD");
    
    private final String name;
    private final String persistentId;
    
    UserStatus(String name, String persistentId) {
        this.name = name;
        this.persistentId = persistentId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPersistentId() {
        return persistentId;
    }
    
    public String getDescriptionKey() {
        return "userstatus." + name + ".description";
    }
}