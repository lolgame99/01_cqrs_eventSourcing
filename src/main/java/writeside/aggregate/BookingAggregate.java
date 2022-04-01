package writeside.aggregate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import writeside.command.BookRoomCommand;
import writeside.command.CancelBookingCommand;
import writeside.domain.*;

import java.util.Optional;
import java.util.UUID;

@Component
public class BookingAggregate {
    //TODO: implement event creation

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public BookingAggregate() {
    }

    public Booking handleBookRoomCommand(BookRoomCommand command) throws Exception {
        Optional<Room> room = roomRepository.getRoomByRoomNumber(command.getRoomNumber());
        if (room.isEmpty()){
            throw new IllegalArgumentException("Room number not valid");
        }

        if (!room.get().isFree(command.getBookingStartTime(),command.getBookingEndTime())){
            throw new Exception("Room already Booked");
        }

        Booking booking = Booking.create(
                UUID.randomUUID(),
                room.get(),
                command.getBookingStartTime(),
                command.getBookingEndTime(),
                PersonalDetails.create(command.getContactName(), command.getNumberOfPeople()));

        bookingRepository.addBooking(booking);
        room.get().addBooking(booking);

        return booking;
    }

    public Booking handleCancelBookingCommand(CancelBookingCommand command) throws  Exception{
        Optional<Booking> booking = bookingRepository.getBookingById(command.getBookingId());
        if (booking.isEmpty()){
            throw new IllegalArgumentException("Booking id not valid");
        }
        booking.get().cancel();
        return booking.get();
    }
}
