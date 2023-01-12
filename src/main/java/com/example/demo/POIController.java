package com.example.demo;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping(value = "/poi")
public class POIController {

    private final POIEventRepository repository;

    private final NotificationService notificationService;

    public POIController(POIEventRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    @GetMapping(value = "/events")
    public Flux<ServerSentEvent<POI>> getStream() {
        return Flux.merge(notificationService.listen("poi_event_notification", POI.class))
                .map(event -> ServerSentEvent.<POI>builder()
                        .retry(Duration.ofSeconds(4L))
                        .event(event.getClass().getSimpleName())
                        .data(event).build());
    }

}
