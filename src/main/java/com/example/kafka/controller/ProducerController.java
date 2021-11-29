package com.example.kafka.controller;

import com.example.kafka.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/v1")
public class ProducerController {


    @Autowired
    private SenderService senderService;

    @GetMapping("/send/{msg}")
    public void sendMessage(@PathVariable String msg)
    {
        senderService.sendMessage(msg);
    }
}
