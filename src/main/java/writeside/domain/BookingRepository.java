package writeside.domain;

import org.springframework.stereotype.Component;

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
}
