package writeside.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import writeside.aggregate.BookingAggregate;
import writeside.command.BookRoomCommand;
import writeside.domain.Booking;

@RestController
public class BookingRestController {

    @Autowired
    BookingAggregate bookingAggregate;

    @PostMapping(value = "/bookRoom", consumes = "application/json", produces = "application/json")
    public Booking bookRoom(@RequestBody BookRoomCommand command){
        Booking booking = null;
        try {
            booking = bookingAggregate.handleBookRoomCommand(command);
        }catch (Exception e){
            e.printStackTrace();
        }
        return booking;
    }
}
