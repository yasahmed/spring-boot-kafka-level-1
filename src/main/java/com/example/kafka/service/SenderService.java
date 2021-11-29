package com.example.kafka.service;

import com.example.kafka.callback.ProducerCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

@Service
public class SenderService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ProducerCallback callback;

    @Value(value = "${kafka.topic}")
    private String topicName;

    public void sendMessage(String message)
    {
            kafkaTemplate.send(topicName, message).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    System.out.println("Sent message=[" + message +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                }
                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("Unable to send message=["
                            + message + "] due to : " + ex.getMessage());
                }
            });

    }
}
