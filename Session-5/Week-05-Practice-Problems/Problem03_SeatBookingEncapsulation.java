public class Problem03_SeatBookingEncapsulation {
    static class CineScreen {
        private final int seatsTotal;
        private int seatsAvailable;

        public CineScreen(int seatsTotal) {
            if (seatsTotal <= 0) {
                throw new IllegalArgumentException(
                        "Seats total must be positive.");
            }

            this.seatsTotal = seatsTotal;
            this.seatsAvailable = seatsTotal;
        }

        public void bookSeat() {
            if (seatsAvailable > 0) {
                seatsAvailable--;
            }
        }

        public void cancelBooking() {
            if (seatsAvailable < seatsTotal) {
                seatsAvailable++;
            }
        }

        public int getSeatsAvailable() {
            return seatsAvailable;
        }
    }

    public static void main(String[] args) {
        CineScreen screen = new CineScreen(2);

        screen.bookSeat();
        screen.bookSeat();
        screen.bookSeat();

        System.out.println("Seats available after bookings: "
                + screen.getSeatsAvailable());

        screen.cancelBooking();
        screen.cancelBooking();
        screen.cancelBooking();

        System.out.println("Seats available after cancellations: "
                + screen.getSeatsAvailable());
    }
}