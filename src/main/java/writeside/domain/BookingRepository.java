package writeside.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class BookingRepository {
    private List<Booking> bookings = new LinkedList<>();

    public List<Booking> getAllBookings(){
        return Collections.unmodifiableList(bookings);
    }

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

    public List<Booking> getBookingsByDate(LocalDateTime dateTime){
        List<Booking> result = new LinkedList<>();
        for (Booking b: bookings) {
            if (dateTime.isAfter(b.getStart()) && dateTime.isBefore(b.getEnd())){
                result.add(b);
            }
        }

        return Collections.unmodifiableList(result);

    }

    public Optional<Booking> removeBookingById(UUID id){
        Booking result = null;
        for (Booking b: bookings) {
            if (b.getId().equals(id)){
                result = b;
            }
        }
        bookings.remove(result);
        return Optional.of(result);
    }
}
