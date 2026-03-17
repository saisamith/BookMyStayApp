import java.util.*;

// ✅ Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// ✅ Validator Class
class InvalidBookingValidator {

    private static final List<String> VALID_ROOMS =
            Arrays.asList("Standard", "Deluxe", "Suite");

    public static void validate(String reservationId, String guestName, String roomType, int roomsAvailable)
            throws InvalidBookingException {

        if (reservationId == null || reservationId.isEmpty()) {
            throw new InvalidBookingException("Reservation ID cannot be empty.");
        }

        if (guestName == null || guestName.isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!VALID_ROOMS.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (roomsAvailable <= 0) {
            throw new InvalidBookingException("No rooms available.");
        }
    }
}

// ✅ Main Class
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        int roomsAvailable = 1;

        try {
            String reservationId = "RES701";
            String guestName = "Samith";
            String roomType = "Deluxe"; // ✅ valid

            // Validate first
            InvalidBookingValidator.validate(reservationId, guestName, roomType, roomsAvailable);

            // ✅ Use YOUR EXISTING Reservation class (2-parameter constructor)
            Reservation reservation = new Reservation(reservationId, guestName);

            roomsAvailable--;

            System.out.println("Booking Successful!");
            System.out.println(reservation);

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }

        System.out.println("System running safely...");
    }
}