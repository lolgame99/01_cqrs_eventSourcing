package eventside.rest;

import eventside.EventRepository;
import eventside.domain.BookingCancelledEvent;
import eventside.domain.BookingCreatedEvent;
import eventside.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventRestController {

    @Autowired
    private EventRepository repository;

    @PostMapping(value = "/createBooking", consumes = "application/json", produces = "application/json")
    public boolean createBooking(@RequestBody BookingCreatedEvent event) {
        repository.processEvent(event);
        System.out.println("[EVENT] Event received: "+event);
        return true;
    }

    @PostMapping(value = "/cancelBooking", consumes = "application/json", produces = "application/json")
    public boolean createBooking(@RequestBody BookingCancelledEvent event) {
        repository.processEvent(event);
        System.out.println("[EVENT] Event received: "+event);
        return true;
    }
}
