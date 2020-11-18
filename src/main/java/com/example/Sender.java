package com.example;

import com.example.avro.AvroMessage;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class Sender {
    Logger logger = LoggerFactory.getLogger(Sender.class);

    private final ProducerAvro<AvroMessage> kafkaAvroProducer;
    private final ProducerJson<JsonMessage> kafkaJsonProducer;

    public Sender(@KafkaClient("producer-avro") ProducerAvro<AvroMessage> kafkaAvroProducer,
                  @KafkaClient("producer-json") ProducerJson<JsonMessage> kafkaJsonProducer) {
        this.kafkaAvroProducer = kafkaAvroProducer;
        this.kafkaJsonProducer = kafkaJsonProducer;
    }

    @Scheduled(fixedDelay = "5s")
    void executeEveryTen() {
        final String id = UUID.randomUUID().toString();
        final String message = "Message";
        final String note = "this message has been scheduled";
        logger.info("Sending message: {}", id);
        kafkaJsonProducer.send(new JsonMessage(id, message, note));
        kafkaAvroProducer.send(new AvroMessage(id, message, note));
    }

}
