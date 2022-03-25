package writeside.domain;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class RoomRepository {
    private List<Room> rooms = new LinkedList<>();

    public List<Room> getAllRooms(){
        return Collections.unmodifiableList(rooms);
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public Optional<Room> getRoomByRoomNumber(String rn){
        for (Room room: rooms) {
            if (room.getRoomNumber().equals(rn)){
                return Optional.of(room);
            }
        }
        return Optional.empty();
    }
}
