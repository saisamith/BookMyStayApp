import java.util.ArrayList;

public class UseCase4RoomSearch {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        ArrayList<Room> rooms = new ArrayList<>();

        rooms.add(new Room("Single Room", 1, 2000));
        rooms.add(new Room("Double Room", 2, 3500));
        rooms.add(new Room("Suite Room", 3, 6000));

        System.out.println("=== Book My Stay App v4.0 ===");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.roomType);

            if (available > 0) {
                room.displayRoomDetails();
                System.out.println("Available: " + available);
                System.out.println();
            }
        }
    }
}