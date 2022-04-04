package readside.dto;

import writeside.domain.PersonalDetails;
import writeside.domain.Room;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookingDTO {
    private final UUID id;
    private final Room room;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final PersonalDetails personalDetails;

    public BookingDTO(UUID id, Room room, LocalDateTime start, LocalDateTime end, PersonalDetails personalDetails) {
        this.id = id;
        this.room = room;
        this.start = start;
        this.end = end;
        this.personalDetails = personalDetails;
    }

    public UUID getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }
}
