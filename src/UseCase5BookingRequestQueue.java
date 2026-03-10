public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("Book My Stay App - Booking Request Queue (v5.1)");

        BookingRequestQueue queue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");

        queue.addRequest(r1);
        queue.addRequest(r2);
        queue.addRequest(r3);

        queue.displayRequests();
    }
}