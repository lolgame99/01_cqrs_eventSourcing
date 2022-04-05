package readside.rest;

import eventside.domain.BookingCancelledEvent;
import eventside.domain.BookingCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import readside.dto.BookingDTO;
import readside.dto.FreeRoomDTO;
import readside.infrastructure.BookingDTORepository;
import readside.infrastructure.FreeRoomDTORepository;
import readside.projection.Projection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class ReadRestController {

    @Autowired
    private FreeRoomDTORepository roomRepository;

    @Autowired
    private BookingDTORepository bookingRepository;

    @Autowired
    private Projection projection;

    @PostMapping(value = "/createBooking", consumes = "application/json", produces = "application/json")
    public boolean createBooking(@RequestBody BookingCreatedEvent event) {
        System.out.println("[READ] Event received: "+event);
        projection.processBookingCreatedEvent(event);
        return true;
    }

    @PostMapping(value = "/cancelBooking", consumes = "application/json", produces = "application/json")
    public boolean createBooking(@RequestBody BookingCancelledEvent event) {
        System.out.println("[READ] Event received: "+event);
        projection.processBookingCancelledEvent(event);
        return true;
    }

    @GetMapping(value = "/bookings")
    public List<BookingDTO> getBookings(@RequestParam String from, @RequestParam String to){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        return bookingRepository.getBookingDTOsByRange(LocalDateTime.parse(from,formatter),LocalDateTime.parse(to,formatter));
    }

    @GetMapping(value = "/freeRooms")
    public List<FreeRoomDTO> getFreeRooms(@RequestParam String from, @RequestParam String to, @RequestParam int personCount){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        return roomRepository.getFreeRooms(LocalDateTime.parse(from,formatter),LocalDateTime.parse(to,formatter),personCount);
    }
}
