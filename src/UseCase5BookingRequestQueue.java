/**
 * Use Case 5: Booking Request Queue (FIFO)
 * Demonstrates first-come-first-served booking requests
 */

public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("Book My Stay App - UC5 Booking Queue");

        BookingRequestQueue queue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Alice", "Single");
        Reservation r2 = new Reservation("Bob", "Double");
        Reservation r3 = new Reservation("Charlie", "Suite");

        queue.addRequest(r1);
        queue.addRequest(r2);
        queue.addRequest(r3);

        queue.displayRequests();
    }
}