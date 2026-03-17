import java.util.*;

class BookingHistory {
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        System.out.println("Booking stored: " + reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }
}

class BookingReportService {

    public void displayAllBookings(List<Reservation> reservations) {
        System.out.println("\n--- Booking History ---");

        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }

    public void generateSummary(List<Reservation> reservations) {
        System.out.println("\n--- Booking Summary Report ---");
        System.out.println("Total Bookings: " + reservations.size());
    }
}

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService report = new BookingReportService();

        // ✅ ONLY 2 PARAMETERS
        Reservation r1 = new Reservation("RES501", "Samith");
        Reservation r2 = new Reservation("RES502", "Rahul");

        history.addReservation(r1);
        history.addReservation(r2);

        report.displayAllBookings(history.getAllReservations());
        report.generateSummary(history.getAllReservations());
    }
}