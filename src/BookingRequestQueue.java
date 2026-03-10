import java.util.LinkedList;
import java.util.Queue;

public class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        queue.add(reservation);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void displayRequests() {

        System.out.println("\nBooking Requests in Queue:");

        for (Reservation r : queue) {
            System.out.println(r.getGuestName() + " - " + r.getRoomType());
        }
    }
}