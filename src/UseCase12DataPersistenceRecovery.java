import java.io.*;
import java.util.*;

// ✅ System State (Serializable)
class SystemState implements Serializable {
    private static final long serialVersionUID = 1L;

    Map<String, String> reservations;
    Map<String, Integer> inventory;

    public SystemState(Map<String, String> reservations, Map<String, Integer> inventory) {
        this.reservations = reservations;
        this.inventory = inventory;
    }
}

// ✅ Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "system_state.ser";

    // Save state
    public static void save(SystemState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(state);
            System.out.println("System state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    // Load state
    public static SystemState load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("System state loaded successfully.");
            return (SystemState) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous state found. Starting fresh.");
        } catch (Exception e) {
            System.out.println("Error loading state. Starting fresh.");
        }
        return null;
    }
}

// ✅ Main Class
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        Map<String, String> reservations;
        Map<String, Integer> inventory;

        // 🔹 Load previous state
        SystemState loadedState = PersistenceService.load();

        if (loadedState != null) {
            reservations = loadedState.reservations;
            inventory = loadedState.inventory;
        } else {
            // Initialize fresh state
            reservations = new HashMap<>();
            inventory = new HashMap<>();

            inventory.put("Standard", 2);
            inventory.put("Deluxe", 1);
        }

        // 🔹 Simulate booking
        String reservationId = "RES1001";
        String roomType = "Standard";

        if (inventory.get(roomType) > 0) {
            reservations.put(reservationId, roomType);
            inventory.put(roomType, inventory.get(roomType) - 1);
            System.out.println("Booking confirmed: " + reservationId);
        } else {
            System.out.println("Booking failed: No rooms available.");
        }

        // 🔹 Display current state
        System.out.println("Reservations: " + reservations);
        System.out.println("Inventory: " + inventory);

        // 🔹 Save state before exit
        SystemState state = new SystemState(reservations, inventory);
        PersistenceService.save(state);
    }
}