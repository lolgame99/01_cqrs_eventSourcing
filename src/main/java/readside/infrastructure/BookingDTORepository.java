package readside.infrastructure;

import org.springframework.stereotype.Component;
import readside.dto.BookingDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class BookingDTORepository {
    private List<BookingDTO> bookings = new ArrayList<>();

    public void addBookingDTO(BookingDTO booking){
        bookings.add(booking);
    }

    public void cancelBookingDTO(BookingDTO booking){
        bookings.remove(booking);
    }

    public Optional<BookingDTO> getBookingDTOById(UUID id){
        for (BookingDTO b:bookings) {
            if (b.getId().equals(id)){
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }

    public List<BookingDTO> getBookingDTOsByRange(LocalDateTime from, LocalDateTime to){
        List<BookingDTO> result = new ArrayList<>();
        for (BookingDTO b:bookings) {
            if (b.getStart().isAfter(from) && b.getEnd().isBefore(to)){
                result.add(b);
            }
        }
        return result;
    }
}
