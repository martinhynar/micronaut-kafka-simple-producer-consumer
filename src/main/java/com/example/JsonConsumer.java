package com.example;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.Body;
import io.micronaut.messaging.annotation.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@KafkaListener(groupId = "example-group-json", offsetReset = OffsetReset.EARLIEST)
public class JsonConsumer {
    Logger logger = LoggerFactory.getLogger(JsonConsumer.class);

    @Topic("topic.json")
    public void receive(@Header("Origin-Format") Optional<String> originFormat,
                        @Header("Transformation") Optional<String> transformation,
                        @Body JsonMessage value) {
//        Gson gson = new Gson();
//        final JsonObject root = gson.fromJson(value, JsonObject.class);
//        final String id = root.get("id").getAsString();
//        final String message = root.get("message").getAsString();
//        Optional<String> note;
//        if (root.has("message")) {
//            note = Optional.of(root.get("note").getAsString());
//        } else {
//            note = Optional.empty();
//        }
        logger.info("JSON Consumer: {}: {} (note: {}) Headers[Origin-Format={}, Transformation={}]",
                value.getId(),
                value.getMessage(),
                value.getNote(),
                originFormat.orElseGet(() -> "none"),
                transformation.orElseGet(() -> "none"));
    }
}
