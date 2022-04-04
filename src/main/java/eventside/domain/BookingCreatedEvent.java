package eventside.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookingCreatedEvent extends Event {
    private final UUID bookingId;
    private final String roomNumber;
    private final LocalDateTime bookingStartTime;
    private final LocalDateTime bookingEndTime;
    private final String contactName;
    private final int numberOfPeople;

    public BookingCreatedEvent(UUID id,String roomNumber, LocalDateTime bookingStartTime, LocalDateTime bookingEndTime, String contactName, int numberOfPeople) {
        this.bookingId = id;
        this.roomNumber = roomNumber;
        this.bookingStartTime = bookingStartTime;
        this.bookingEndTime = bookingEndTime;
        this.contactName = contactName;
        this.numberOfPeople = numberOfPeople;
        this.setTimestamp(LocalDateTime.now());
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public LocalDateTime getBookingStartTime() {
        return bookingStartTime;
    }

    public LocalDateTime getBookingEndTime() {
        return bookingEndTime;
    }

    public String getContactName() {
        return contactName;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public UUID getBookingId() {
        return bookingId;
    }
}