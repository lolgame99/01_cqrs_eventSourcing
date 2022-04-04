package readside.dto;

import java.time.LocalDateTime;

public class FreeRoomDTO {
    private final String roomNumber;
    private final int maxPeople;
    private LocalDateTime from;
    private LocalDateTime to;

    public FreeRoomDTO(String roomNumber, int maxPeople, LocalDateTime from, LocalDateTime to) {
        this.roomNumber = roomNumber;
        this.maxPeople = maxPeople;
        this.from = from;
        this.to = to;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }
}
