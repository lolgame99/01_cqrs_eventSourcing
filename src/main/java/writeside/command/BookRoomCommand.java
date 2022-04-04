package writeside.command;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookRoomCommand {
    private final String roomNumber;
    private final LocalDateTime bookingStartTime;
    private final LocalDateTime bookingEndTime;
    private final String contactName;
    private final int numberOfPeople;

    public BookRoomCommand(String roomNumber, LocalDateTime bookingStartTime, LocalDateTime bookingEndTime, String contactName, int numberOfPeople) {
        this.roomNumber = roomNumber;
        this.bookingStartTime = bookingStartTime;
        this.bookingEndTime = bookingEndTime;
        this.contactName = contactName;
        this.numberOfPeople = numberOfPeople;
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


}
