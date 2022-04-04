package readside.rest;

import eventside.domain.BookingCancelledEvent;
import eventside.domain.BookingCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import readside.dto.BookingDTO;
import readside.dto.FreeRoomDTO;
import readside.infrastructure.BookingDTORepository;
import readside.infrastructure.FreeRoomDTORepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ReadRestController {

    @Autowired
    private FreeRoomDTORepository roomRepository;

    @Autowired
    private BookingDTORepository bookingRepository;

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

    @GetMapping(value = "/bookings")
    public List<BookingDTO> getBookings(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to){
        return bookingRepository.getBookingDTOsByRange(from,to);
    }

    @GetMapping(value = "/freeRooms")
    public List<FreeRoomDTO> getFreeRooms(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to, @RequestParam int personCount){
        return roomRepository.getFreeRooms(from,to,personCount);
    }
}
