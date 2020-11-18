package com.example;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.Header;
import org.apache.avro.reflect.AvroSchema;

@KafkaClient(id = "producer-avro")
@Header(name = "Origin-Format", value = "avro")
public interface ProducerAvro<V> {
    @Topic("topic.avro")
    void send(V message);
}
