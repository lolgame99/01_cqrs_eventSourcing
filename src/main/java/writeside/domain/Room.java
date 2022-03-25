package writeside.domain;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Room {
    private final String roomNumber;
    private final int maxPeople;
    private final List<Booking> bookings = new LinkedList<>();

    public Room(String roomNumber, int maxPeople) {
        this.roomNumber = roomNumber;
        this.maxPeople = maxPeople;
    }

    public boolean isFree(LocalDateTime start, LocalDateTime end, UUID bookingId) {
        for (Booking booking : bookings) {
            if (!booking.getId().equals(bookingId)
                    && booking.getState().equals(Booking.State.ACTIVE) &&
                    !booking.getStart().isAfter(end) && !start.isAfter(booking.getEnd())
            ) {
                return false;
            }
        }

        return true;
    }

    public void addBooking(Booking booking) {
        if (!roomNumber.equals(booking.getRoom().getRoomNumber())) {
            throw new IllegalArgumentException("Booking does not belong to this room");
        }

        bookings.add(booking);
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public List<Booking> getBookings() {
        return Collections.unmodifiableList(bookings);
    }
}
