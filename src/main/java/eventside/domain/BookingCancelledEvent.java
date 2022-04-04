package eventside.domain;

import java.time.LocalDateTime;
import java.util.UUID;


public class BookingCancelledEvent extends Event{
    private UUID bookingId;

    public BookingCancelledEvent(){}

    public BookingCancelledEvent(String bookingId) {
        this.bookingId = UUID.fromString(bookingId);
        this.setTimestamp(LocalDateTime.now());
    }

    public BookingCancelledEvent(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public UUID getBookingId() {
        return bookingId;
    }

}