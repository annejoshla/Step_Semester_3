import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Problem04_HotelBookingSystem {
    static abstract class Room {
        private final String number;
        private final List<Reservation> reservations =
                new ArrayList<>();

        protected Room(String number) {
            this.number = number;
        }

        public String getNumber() {
            return number;
        }

        public abstract double pricePerNight();

        public boolean isAvailable(
                LocalDate start,
                LocalDate end) {

            for (Reservation reservation : reservations) {
                if (!reservation.isCancelled()
                        && datesOverlap(
                        start,
                        end,
                        reservation.getStartDate(),
                        reservation.getEndDate())) {
                    return false;
                }
            }

            return true;
        }

        public void addReservation(Reservation reservation) {
            reservations.add(reservation);
        }

        public void removeReservation(Reservation reservation) {
            reservations.remove(reservation);
        }

        private boolean datesOverlap(
                LocalDate firstStart,
                LocalDate firstEnd,
                LocalDate secondStart,
                LocalDate secondEnd) {

            return firstStart.isBefore(secondEnd)
                    && secondStart.isBefore(firstEnd);
        }
    }

    static class StandardRoom extends Room {
        public StandardRoom(String number) {
            super(number);
        }

        @Override
        public double pricePerNight() {
            return 100.0;
        }
    }

    static class DeluxeRoom extends Room {
        public DeluxeRoom(String number) {
            super(number);
        }

        @Override
        public double pricePerNight() {
            return 180.0;
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

    static class Reservation {
        private final Customer customer;
        private final Room room;
        private final LocalDate startDate;
        private final LocalDate endDate;
        private boolean cancelled;

        public Reservation(
                Customer customer,
                Room room,
                LocalDate startDate,
                LocalDate endDate) {

            this.customer = customer;
            this.room = room;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public double calculatePrice() {
            long nights = endDate.toEpochDay()
                    - startDate.toEpochDay();

            return nights * room.pricePerNight();
        }

        public void cancel(LocalDate cancellationDate) {
            if (cancellationDate.isBefore(startDate)) {
                cancelled = true;
                room.removeReservation(this);

                System.out.printf(
                        "Reservation for %s, Room %s cancelled."
                                + "%n",
                        customer.getName(),
                        room.getNumber());
            } else {
                System.out.println(
                        "Cancellation deadline has passed.");
            }
        }

        public boolean isCancelled() {
            return cancelled;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }
    }

    static class Hotel {
        public Reservation reserve(
                Customer customer,
                Room room,
                LocalDate startDate,
                LocalDate endDate) {

            if (!room.isAvailable(startDate, endDate)) {
                System.out.printf(
                        "Room %s is not available.%n",
                        room.getNumber());
                return null;
            }

            Reservation reservation = new Reservation(
                    customer,
                    room,
                    startDate,
                    endDate);

            room.addReservation(reservation);

            System.out.printf(
                    "Reservation confirmed for %s, Room %s. "
                            + "Price: $%.2f%n",
                    customer.getName(),
                    room.getNumber(),
                    reservation.calculatePrice());

            return reservation;
        }
    }

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Customer customerA = new Customer("Customer A");
        Customer customerB = new Customer("Customer B");

        Room room101 = new StandardRoom("101");

        Reservation reservation = hotel.reserve(
                customerA,
                room101,
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 5));

        hotel.reserve(
                customerB,
                room101,
                LocalDate.of(2026, 1, 3),
                LocalDate.of(2026, 1, 7));

        if (reservation != null) {
            reservation.cancel(LocalDate.of(2025, 12, 20));
        }
    }
}