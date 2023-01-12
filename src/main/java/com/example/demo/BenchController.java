package com.example.demo;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping(value = "/bench")
public class BenchController {

    private final NotificationService notificationService;

    public BenchController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping(value = "/events")
    public Flux<ServerSentEvent<Account>> getStream() {
        return Flux.merge(notificationService.listen("accounts_event_notification", Account.class))
                .map(event -> ServerSentEvent.<Account>builder()
                        .retry(Duration.ofSeconds(4L))
                        .event(event.getClass().getSimpleName())
                        .data(event).build());
    }

}
