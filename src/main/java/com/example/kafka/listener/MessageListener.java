package com.example.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    @KafkaListener(topics = "first_topic",containerFactory = "kafkaListenerContainerFactory")
    public void listenWithHeaders(
            @Header ConsumerRecord record) {
        System.out.println(
                "Received Message: " + record.value()
                        + "from topic: " + record.topic());
    }
}
