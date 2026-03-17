import java.util.*;

// Booking Request
class BookingRequest {
    String reservationId;
    String guestName;
    String roomType;

    public BookingRequest(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Shared Booking Processor
class ConcurrentBookingProcessor {

    // Shared queue
    private Queue<BookingRequest> bookingQueue = new LinkedList<>();

    // Shared inventory
    private Map<String, Integer> inventory = new HashMap<>();

    public ConcurrentBookingProcessor() {
        inventory.put("Standard", 1);
        inventory.put("Deluxe", 1);
    }

    // Add booking request (synchronized)
    public synchronized void addRequest(BookingRequest request) {
        bookingQueue.add(request);
        System.out.println("Request added: " + request.reservationId);
    }

    // Process booking (critical section)
    public synchronized void processRequest() {

        if (bookingQueue.isEmpty()) {
            return;
        }

        BookingRequest request = bookingQueue.poll();

        if (request == null) return;

        String roomType = request.roomType;

        if (inventory.containsKey(roomType) && inventory.get(roomType) > 0) {

            // Allocate room
            inventory.put(roomType, inventory.get(roomType) - 1);

            System.out.println(Thread.currentThread().getName() +
                    " booked " + roomType +
                    " for " + request.guestName +
                    " (ID: " + request.reservationId + ")");

        } else {
            System.out.println(Thread.currentThread().getName() +
                    " FAILED booking for " + request.guestName +
                    " (No " + roomType + " rooms available)");
        }
    }
}

// Worker Thread
class BookingWorker extends Thread {

    private ConcurrentBookingProcessor processor;

    public BookingWorker(ConcurrentBookingProcessor processor, String name) {
        super(name);
        this.processor = processor;
    }

    public void run() {
        for (int i = 0; i < 2; i++) {
            processor.processRequest();

            try {
                Thread.sleep(100); // simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Main Class
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        ConcurrentBookingProcessor processor = new ConcurrentBookingProcessor();

        // Add requests (simulating multiple users)
        processor.addRequest(new BookingRequest("RES901", "Samith", "Standard"));
        processor.addRequest(new BookingRequest("RES902", "Rahul", "Standard"));
        processor.addRequest(new BookingRequest("RES903", "Anita", "Deluxe"));
        processor.addRequest(new BookingRequest("RES904", "Kiran", "Deluxe"));

        // Create multiple threads
        BookingWorker t1 = new BookingWorker(processor, "Thread-1");
        BookingWorker t2 = new BookingWorker(processor, "Thread-2");

        // Start threads
        t1.start();
        t2.start();

        // Wait for threads to finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nAll booking requests processed safely.");
    }
}