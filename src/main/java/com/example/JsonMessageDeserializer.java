package com.example;

import com.google.gson.Gson;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class JsonMessageDeserializer<T> implements Deserializer<JsonMessage> {
    private final Gson gson = new Gson();

    private Class<T> tClass;


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public JsonMessage deserialize(String topic, byte[] data) {
        if (data == null)
            return null;

        JsonMessage object;
        try {
            object = gson.fromJson(new InputStreamReader(new ByteArrayInputStream(data)), JsonMessage.class);
        } catch (Exception e) {
            throw new SerializationException(e);
        }
        return object;
    }

    @Override
    public void close() {

    }
}
