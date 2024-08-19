package com.example.person_service.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // Регистрируем модуль JavaTimeModule для поддержки типов Java 8 Date/Time API
        objectMapper.registerModule(new JavaTimeModule());
        // Отключаем запись дат как временных меток
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static String convertObjectToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при сериализации объекта в JSON", e);
        }
    }

    public static <T> T convertJsonToObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при десериализации JSON в объект", e);
        }
    }
}