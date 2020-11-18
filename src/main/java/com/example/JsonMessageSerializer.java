package com.example;

import com.google.gson.Gson;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class JsonMessageSerializer implements Serializer<JsonMessage> {
    private final Gson gson = new Gson();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, JsonMessage data) {
        return new byte[0];
    }

    @Override
    public byte[] serialize(String topic, Headers headers, JsonMessage data) {
        if (data == null)
            return null;
        try {
            return gson.toJson(data).getBytes();
        } catch (Exception e) {
            throw new SerializationException("Error serializing JSON", e);
        }
    }

    @Override
    public void close() {

    }
}
