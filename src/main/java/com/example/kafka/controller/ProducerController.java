package com.example.kafka.controller;

import com.example.kafka.dto.SmsDTO;
import com.example.kafka.service.SenderService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/v1")
public class ProducerController {

    private SenderService senderService;
    public ProducerController(SenderService senderService)
    {
        this.senderService=senderService;
    }


    @GetMapping("/send/{msg}")
    public void sendMessage(@PathVariable String msg)
    {
        senderService.sendMessage(new SmsDTO("from","to",msg));
    }
}
