package readside.rest;

import eventside.domain.BookingCancelledEvent;
import eventside.domain.BookingCreatedEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadRestController {
    @PostMapping(value = "/createBooking", consumes = "application/json", produces = "application/json")
    public boolean createBooking(@RequestBody BookingCreatedEvent event) {
        System.out.println("[READ] Event received: "+event);
        //TODO: implement event projection
        return true;
    }

    @PostMapping(value = "/cancelBooking", consumes = "application/json", produces = "application/json")
    public boolean createBooking(@RequestBody BookingCancelledEvent event) {
        System.out.println("[READ] Event received: "+event);
        //TODO: implement event projection
        return true;
    }
}
