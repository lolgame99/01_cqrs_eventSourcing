package writeside.domain;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Room {
    private final String roomNumber;
    private final int maxPeople;

    public Room(String roomNumber, int maxPeople) {
        this.roomNumber = roomNumber;
        this.maxPeople = maxPeople;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

}
