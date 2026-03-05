package com.LiterAlura;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Conversor {

    private ObjectMapper mapper = new ObjectMapper();

    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return mapper.readValue(json, clase);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
