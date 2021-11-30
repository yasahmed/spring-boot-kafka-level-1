package com.example.kafka.service;

import com.example.kafka.dto.SmsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Service
public class SenderService {
    @Autowired
    private KafkaTemplate<String, SmsDTO> kafkaTemplate;


    @Value(value = "${kafka.topic}")
    private String topicName;

    public void sendMessage(SmsDTO sms)
    {
            kafkaTemplate.send(topicName, "",sms).addCallback(new ListenableFutureCallback<SendResult<String, SmsDTO>>() {

                @Override
                public void onSuccess(SendResult<String, SmsDTO> result) {
                    System.out.println("Sent message=[" + sms.getMessage() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                }
                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("Unable to send message=["
                            + sms.getMessage() + "] due to : " + ex.getMessage());
                }
            });

    }
}
