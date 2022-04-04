package readside.projection;

import eventside.domain.BookingCancelledEvent;
import eventside.domain.BookingCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import readside.dto.BookingDTO;
import readside.dto.FreeRoomDTO;
import readside.infrastructure.BookingDTORepository;
import readside.infrastructure.FreeRoomDTORepository;
import writeside.domain.PersonalDetails;

import java.util.List;
import java.util.Optional;

@Component
public class Projection {
    @Autowired
    private FreeRoomDTORepository roomRepository;

    @Autowired
    private BookingDTORepository bookingRepository;

    public void processBookingCreatedEvent(BookingCreatedEvent e){
        List<FreeRoomDTO> freeRooms = roomRepository.getFreeRooms(e.getBookingStartTime(), e.getBookingEndTime(),e.getNumberOfPeople());

        BookingDTO booking = new BookingDTO(
                e.getBookingId(),
                e.getRoomNumber(),
                e.getBookingStartTime(),
                e.getBookingEndTime(),
                PersonalDetails.create(e.getContactName(),e.getNumberOfPeople())
        );
        bookingRepository.addBookingDTO(booking);
    }

    public void processBookingCancelledEvent(BookingCancelledEvent e){
        Optional<BookingDTO> booking = bookingRepository.getBookingDTOById(e.getBookingId());
        if (booking.isPresent()){
            bookingRepository.cancelBookingDTO(booking.get());
        }
    }
}
