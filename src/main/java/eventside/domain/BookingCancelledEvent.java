package eventside.domain;

import java.util.UUID;


public class BookingCancelledEvent {
    private UUID bookingId;

    public BookingCancelledEvent(){}

    public BookingCancelledEvent(String bookingId) {
        this.bookingId = UUID.fromString(bookingId);
    }

    public BookingCancelledEvent(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public UUID getBookingId() {
        return bookingId;
    }

}