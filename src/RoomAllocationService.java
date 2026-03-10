import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class RoomAllocationService {

    private HashMap<String, Set<String>> allocatedRooms;
    private int roomCounter = 1;

    public RoomAllocationService() {
        allocatedRooms = new HashMap<>();
    }

    public String allocateRoom(String roomType) {

        String roomId = roomType.substring(0,1).toUpperCase() + roomCounter++;

        allocatedRooms.putIfAbsent(roomType, new HashSet<>());
        allocatedRooms.get(roomType).add(roomId);

        return roomId;
    }

    public void displayAllocatedRooms() {

        System.out.println("\nAllocated Rooms:");

        for(String type : allocatedRooms.keySet()) {
            System.out.println(type + " -> " + allocatedRooms.get(type));
        }
    }
}