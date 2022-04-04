package eventside.domain;

import java.time.LocalDateTime;
import java.util.UUID;


public class BookingCancelledEvent extends Event{
    private UUID bookingId;

    public BookingCancelledEvent(){}

    public BookingCancelledEvent(UUID bookingId) {
        this.bookingId = bookingId;
        this.setTimestamp(LocalDateTime.now());
    }

    public UUID getBookingId() {
        return bookingId;
    }

}