package writeside.command;

import java.util.UUID;

public class CancelBookingCommand {
    private UUID bookingId;

    public CancelBookingCommand(){}

    public CancelBookingCommand(String bookingId) {
        this.bookingId = UUID.fromString(bookingId);
    }

    public CancelBookingCommand(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public UUID getBookingId() {
        return bookingId;
    }

}
