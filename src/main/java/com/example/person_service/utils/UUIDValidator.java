package com.example.person_service.utils;

import java.util.UUID;

public class UUIDValidator {
    public static boolean isValidUUID(String uuid){
        try{
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException exception){
            return false;
        }
    }
}
