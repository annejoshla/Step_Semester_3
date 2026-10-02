import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem03_CampusPremiereTicketCounter {
    interface Seat {
        String getId();

        double getPrice();
    }

    static class RegularSeat implements Seat {
        private final String id;

        public RegularSeat(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public double getPrice() {
            return 150.0;
        }
    }

    static class PremiumSeat implements Seat {
        private final String id;

        public PremiumSeat(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public double getPrice() {
            return 250.0;
        }
    }

    static class ReclinerSeat implements Seat {
        private final String id;

        public ReclinerSeat(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public double getPrice() {
            return 400.0;
        }
    }

    static class Customer {
        private final String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class Show {
        private final String name;
        private final LocalDateTime startTime;
        private final Set<String> bookedSeatIds =
                new HashSet<>();

        public Show(String name, LocalDateTime startTime) {
            this.name = name;
            this.startTime = startTime;
        }

        public String getName() {
            return name;
        }

        public boolean areAvailable(List<Seat> seats) {
            for (Seat seat : seats) {
                if (bookedSeatIds.contains(seat.getId())) {
                    return false;
                }
            }

            return true;
        }

        public void reserveSeats(List<Seat> seats) {
            for (Seat seat : seats) {
                bookedSeatIds.add(seat.getId());
            }
        }

        public void releaseSeats(List<Seat> seats) {
            for (Seat seat : seats) {
                bookedSeatIds.remove(seat.getId());
            }
        }

        public boolean hasStarted() {
            return !LocalDateTime.now().isBefore(startTime);
        }
    }

    static class Booking {
        private final Customer customer;
        private final Show show;
        private final List<Seat> seats;
        private boolean cancelled;

        public Booking(
                Customer customer,
                Show show,
                List<Seat> seats) {
            this.customer = customer;
            this.show = show;
            this.seats = new ArrayList<>(seats);
        }

        public double total() {
            double total = 0;

            for (Seat seat : seats) {
                total += seat.getPrice();
            }

            return total;
        }

        public void cancel() {
            if (show.hasStarted()) {
                System.out.println(
                        "Cannot cancel after the show has started.");
                return;
            }

            if (!cancelled) {
                cancelled = true;
                show.releaseSeats(seats);

                System.out.printf(
                        "%s's booking cancelled. Seats released.%n",
                        customer.getName());
            }
        }
    }

    static class TicketCounter {
        public Booking book(
                Customer customer,
                Show show,
                List<Seat> seats) {

            if (seats.isEmpty() || seats.size() > 6) {
                System.out.println(
                        "A booking must contain 1 to 6 seats.");
                return null;
            }

            if (!show.areAvailable(seats)) {
                System.out.println(
                        "One or more selected seats are already "
                                + "booked for this show.");
                return null;
            }

            show.reserveSeats(seats);

            Booking booking = new Booking(
                    customer,
                    show,
                    seats);

            System.out.printf(
                    "Booking confirmed for %s. Total: ₹%.2f%n",
                    customer.getName(),
                    booking.total());

            return booking;
        }
    }

    public static void main(String[] args) {
        TicketCounter counter = new TicketCounter();

        Show show = new Show(
                "Campus Premiere",
                LocalDateTime.now().plusHours(2));

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        List<Seat> ashaSeats = List.of(
                new RegularSeat("A1"),
                new RegularSeat("A2"),
                new PremiumSeat("F5"));

        Booking ashaBooking =
                counter.book(asha, show, ashaSeats);

        counter.book(
                ravi,
                show,
                List.of(new RegularSeat("A2")));

        counter.book(
                ravi,
                show,
                List.of(new ReclinerSeat("R1")));

        if (ashaBooking != null) {
            ashaBooking.cancel();
        }

        counter.book(
                neha,
                show,
                List.of(new RegularSeat("A2")));
    }
}