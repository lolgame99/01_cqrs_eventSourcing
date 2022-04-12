package writeside.domain;

import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class BookingRepository {
    private List<Booking> bookings = new LinkedList<>();

    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    public Optional<Booking> getBookingById(UUID id){
        for (Booking b: bookings) {
            if (b.getId().equals(id)){
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }

    public List<Booking> getBookingByRoom(Room room){
        List<Booking> result = new LinkedList<>();
        for (Booking b: bookings) {
            if (b.getRoom().getRoomNumber().equals(room.getRoomNumber())){
                result.add(b);
            }
        }
        return result;
    }
}
