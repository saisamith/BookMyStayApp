public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocator = new RoomAllocationService();

        queue.addRequest(new Reservation("Alice","Single"));
        queue.addRequest(new Reservation("Bob","Double"));
        queue.addRequest(new Reservation("Charlie","Single"));

        while(!queue.isEmpty()) {

            Reservation request = queue.getNextRequest();

            String roomType = request.getRoomType();

            if(inventory.getAvailability(roomType) > 0) {

                String roomId = allocator.allocateRoom(roomType);

                inventory.decrementRoom(roomType);

                System.out.println("Reservation Confirmed:");
                System.out.println("Guest: " + request.getGuestName());
                System.out.println("Room Type: " + roomType);
                System.out.println("Room ID: " + roomId);
                System.out.println("---------------------");

            }
            else {

                System.out.println("No rooms available for "
                        + request.getGuestName()
                        + " (" + roomType + ")");
            }
        }

        allocator.displayAllocatedRooms();
        inventory.displayInventory();
    }
}