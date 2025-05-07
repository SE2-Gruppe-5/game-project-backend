package com.se2gruppe5.risikobackend.sse.controllers;

import com.se2gruppe5.risikobackend.sse.services.SseBroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Controller
public class SseController {
    private final SseBroadcastService sseBroadcaster;

    @Autowired
    public SseController(SseBroadcastService sseBroadcaster) {
        this.sseBroadcaster = sseBroadcaster;
    }

    @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream() {
        return Flux.create(sink -> {
            UUID uuid = sseBroadcaster.addSink(sink);
            sink.next(uuid.toString()); // Send the UUID to the client, so they can use it to send requests
        });
    }
}
