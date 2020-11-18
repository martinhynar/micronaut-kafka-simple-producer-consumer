package com.example;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.Header;

@KafkaClient(id = "producer-json")
@Header(name = "Origin-Format", value = "json")
public interface ProducerJson<V> {
    @Topic("topic.json")
    void send(V message);
}
