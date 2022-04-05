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

import java.time.LocalDateTime;
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

        for (FreeRoomDTO r:freeRooms) {
            if (r.getRoomNumber().equals(e.getRoomNumber())){
                FreeRoomDTO roomBefore = new FreeRoomDTO(
                        r.getRoomNumber(),
                        r.getMaxPeople(),
                        r.getFrom(),
                        e.getBookingStartTime()
                );
                roomRepository.addFreeRoom(roomBefore);

                FreeRoomDTO roomAfter = new FreeRoomDTO(
                        r.getRoomNumber(),
                        r.getMaxPeople(),
                        e.getBookingEndTime(),
                        r.getTo()
                );
                roomRepository.addFreeRoom(roomAfter);
                roomRepository.removeFreeRoom(r);
            }
        }
    }

    public void processBookingCancelledEvent(BookingCancelledEvent e){
        Optional<BookingDTO> booking = bookingRepository.getBookingDTOById(e.getBookingId());
        LocalDateTime newFrom = null;
        LocalDateTime newTo = null;

        if (booking.isPresent()){
            bookingRepository.cancelBookingDTO(booking.get());
        }
        List<FreeRoomDTO> freeRoomsBefore = roomRepository.getFreeRooms(LocalDateTime.MIN, booking.get().getStart(), booking.get().getPersonalDetails().getNumberOfPeople());

        for (FreeRoomDTO r:freeRoomsBefore) {
            if (r.getRoomNumber().equals(booking.get().getRoomNumber())&&r.getTo().equals(booking.get().getStart())){
                roomRepository.removeFreeRoom(r);
                newFrom = r.getFrom();
            }
        }

        List<FreeRoomDTO> freeRoomsAfter = roomRepository.getFreeRooms(booking.get().getEnd(), LocalDateTime.MAX, booking.get().getPersonalDetails().getNumberOfPeople());

        for (FreeRoomDTO r:freeRoomsAfter) {
            if (r.getRoomNumber().equals(booking.get().getRoomNumber())&&r.getFrom().equals(booking.get().getEnd())){
                roomRepository.removeFreeRoom(r);
                newTo = r.getTo();

                roomRepository.addFreeRoom(new FreeRoomDTO(
                        booking.get().getRoomNumber(),
                        r.getMaxPeople(),
                        newFrom,
                        newTo
                ));
            }
        }

    }
}
