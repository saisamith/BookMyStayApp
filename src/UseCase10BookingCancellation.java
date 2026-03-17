import java.util.*;

// ✅ Cancellation Service
class CancellationService {

    // Stores active bookings
    private Map<String, String> reservations = new HashMap<>();

    // Inventory (roomType → count)
    private Map<String, Integer> inventory = new HashMap<>();

    // Stack for rollback (room IDs)
    private Stack<String> rollbackStack = new Stack<>();

    public CancellationService() {
        // Initial inventory
        inventory.put("Standard", 2);
        inventory.put("Deluxe", 1);
    }

    // Simulate booking
    public void confirmBooking(String reservationId, String roomType) {
        if (!inventory.containsKey(roomType) || inventory.get(roomType) <= 0) {
            System.out.println("Booking failed: No rooms available.");
            return;
        }

        reservations.put(reservationId, roomType);
        inventory.put(roomType, inventory.get(roomType) - 1);

        System.out.println("Booking confirmed: " + reservationId + " (" + roomType + ")");
    }

    // Cancel booking
    public void cancelBooking(String reservationId) {

        // ✅ Validate reservation exists
        if (!reservations.containsKey(reservationId)) {
            System.out.println("Cancellation failed: Invalid reservation ID.");
            return;
        }

        String roomType = reservations.get(reservationId);

        // ✅ Push to rollback stack
        rollbackStack.push(reservationId);

        // ✅ Restore inventory
        inventory.put(roomType, inventory.get(roomType) + 1);

        // ✅ Remove booking
        reservations.remove(reservationId);

        System.out.println("Booking cancelled: " + reservationId);
    }

    // Display current state
    public void displayStatus() {
        System.out.println("\nCurrent Reservations: " + reservations);
        System.out.println("Inventory: " + inventory);
        System.out.println("Rollback Stack: " + rollbackStack);
    }
}

// ✅ Main Class
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        CancellationService service = new CancellationService();

        // Simulate bookings
        service.confirmBooking("RES801", "Standard");
        service.confirmBooking("RES802", "Deluxe");

        service.displayStatus();

        // Cancel booking
        service.cancelBooking("RES801");

        service.displayStatus();

        // Invalid cancellation
        service.cancelBooking("RES999");
    }
}