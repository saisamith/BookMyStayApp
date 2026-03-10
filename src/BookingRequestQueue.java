import java.util.LinkedList;
import java.util.Queue;

/**
 * BookingRequestQueue manages booking requests using FIFO principle.
 * Requests are stored in arrival order.
 *
 * @author Samith
 * @version 5.0
 */

public class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Booking request added for: " + reservation.getGuestName());
    }

    public void displayRequests() {
        if (requestQueue.isEmpty()) {
            System.out.println("No booking requests in queue.");
            return;
        }

        System.out.println("\nBooking Requests in Queue:");
        for (Reservation r : requestQueue) {
            r.displayReservation();
        }
    }
}