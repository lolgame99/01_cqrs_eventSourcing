package readside.infrastructure;

import org.springframework.stereotype.Component;
import readside.dto.FreeRoomDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class FreeRoomDTORepository {
    List<FreeRoomDTO> freeRooms = new ArrayList<>();

    public FreeRoomDTORepository(){
        freeRooms.add(new FreeRoomDTO("102",2, LocalDateTime.MIN,LocalDateTime.MAX));
        freeRooms.add(new FreeRoomDTO("103",3, LocalDateTime.MIN,LocalDateTime.MAX));
        freeRooms.add(new FreeRoomDTO("104",4, LocalDateTime.MIN,LocalDateTime.MAX));
    }

    public List<FreeRoomDTO> getFreeRooms(LocalDateTime from, LocalDateTime to, int personCount){
        List<FreeRoomDTO> result = new ArrayList<>();
        for (FreeRoomDTO r:freeRooms) {
            if (
                    r.getMaxPeople() >= personCount &&
                    r.getFrom().isBefore(from) &&
                    r.getTo().isAfter(to)){
                result.add(r);
            }
        }
        return result;
    }

    public void addFreeRoom(FreeRoomDTO room){
        freeRooms.add(room);
    }

    public void removeFreeRoom(FreeRoomDTO room){
        freeRooms.remove(room);
    }
}
