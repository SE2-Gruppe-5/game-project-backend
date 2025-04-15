package com.se2gruppe5.risikobackend.chat.controllers;

import com.se2gruppe5.risikobackend.chat.messages.ChatMessage;
import com.se2gruppe5.risikobackend.sse.services.SseBroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private final SseBroadcastService sseBroadcaster;

    @Autowired
    public ChatController(SseBroadcastService sseBroadcaster) {
        this.sseBroadcaster = sseBroadcaster;
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void chat(@RequestParam String message) {
        System.out.println("Received message: " + message);
        sseBroadcaster.broadcast(new ChatMessage(message));
    }
}
