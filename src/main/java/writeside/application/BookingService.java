package writeside.application;

import eventside.domain.BookingCancelledEvent;
import eventside.domain.BookingCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import writeside.WriteEventPublisher;
import writeside.command.BookRoomCommand;
import writeside.command.CancelBookingCommand;
import writeside.domain.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class BookingService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private WriteEventPublisher writeEventPublisher;

    public BookingService() {
    }

    public Booking handleBookRoomCommand(BookRoomCommand command) throws Exception {
        Optional<Room> room = roomRepository.getRoomByRoomNumber(command.getRoomNumber());
        if (room.isEmpty()){
            throw new IllegalArgumentException("Room number not valid");
        }

        if (!isRoomFree(room.get(),command.getBookingStartTime(),command.getBookingEndTime())){
            throw new Exception("Room already Booked");
        }

        Booking booking = Booking.create(
                UUID.randomUUID(),
                room.get(),
                command.getBookingStartTime(),
                command.getBookingEndTime(),
                PersonalDetails.create(command.getContactName(), command.getNumberOfPeople()));

        bookingRepository.addBooking(booking);
        System.out.println("[WRITE] Created Booking "+booking.getId());

        BookingCreatedEvent event = new BookingCreatedEvent(
                booking.getId(),
                command.getRoomNumber(),
                command.getBookingStartTime(),
                command.getBookingEndTime(),
                command.getContactName(),
                command.getNumberOfPeople());
        writeEventPublisher.publishBookRoomEvent(event);

        return booking;
    }

    public Booking handleCancelBookingCommand(CancelBookingCommand command) throws  Exception{
        Optional<Booking> booking = bookingRepository.getBookingById(command.getBookingId());
        if (booking.isEmpty()){
            throw new IllegalArgumentException("Booking id not valid");
        }
        booking.get().cancel();
        System.out.println("[WRITE] Canceled Booking "+command.getBookingId());

        BookingCancelledEvent event = new BookingCancelledEvent(command.getBookingId());
        writeEventPublisher.publishCancelBookingEvent(event);

        return booking.get();
    }

    private Boolean isRoomFree(Room room, LocalDateTime start, LocalDateTime end){
        Boolean isFree = true;
        List<Booking> roomBookings = bookingRepository.getBookingByRoom(room);

        for (Booking b: roomBookings) {
            if (b.getState().equals(Booking.State.ACTIVE) &&
                    !b.getStart().isAfter(end) && !start.isAfter(b.getEnd())){
                isFree = false;
            }
        }
        return isFree;
    }
}
