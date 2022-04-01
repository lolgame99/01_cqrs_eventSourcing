package writeside.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import writeside.aggregate.BookingService;
import writeside.command.BookRoomCommand;
import writeside.command.CancelBookingCommand;
import writeside.domain.Booking;

@RestController
public class BookingRestController {

    @Autowired
    BookingService bookingService;

    @PostMapping(value = "/createBooking", consumes = "application/json", produces = "application/json")
    public Boolean createBooking(@RequestBody BookRoomCommand command){
        Booking booking = null;
        try {
            booking = bookingService.handleBookRoomCommand(command);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    @PostMapping(value = "/cancelBooking", consumes = "application/json", produces = "application/json")
    public Booking cancelBooking(@RequestBody CancelBookingCommand command){
        Booking booking = null;
        try {
            booking = bookingService.handleCancelBookingCommand(command);
        }catch (Exception e){
            e.printStackTrace();
        }
        return booking;
    }
}
