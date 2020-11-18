package com.example;

import com.example.avro.AvroMessage;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.Body;
import io.micronaut.messaging.annotation.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@KafkaListener(groupId = "example-group-avro", offsetReset = OffsetReset.EARLIEST)
public class AvroConsumer {
    Logger logger = LoggerFactory.getLogger(AvroConsumer.class);

    @Topic("topic.avro")
    public void receive(@Header("Origin-Format") Optional<String> originFormat,
                        @Header("Transformation") Optional<String> transformation,
                        @Body AvroMessage value) {
        logger.info("AVRO Consumer: {}: {} (note: {}) Headers[Origin-Format={}, Transformation={}]",
                value.getId(),
                value.getMessage(),
                value.getNote(),
                originFormat.orElseGet(() -> "none"),
                transformation.orElseGet(() -> "none"));
    }
}
