public class Problem03_BookCopyCirculationGuard {
    static class BookInventory {
        private final int copiesTotal;
        private int copiesAvailable;

        public BookInventory(int copiesTotal) {
            if (copiesTotal <= 0) {
                throw new IllegalArgumentException(
                        "Copies total must be positive.");
            }

            this.copiesTotal = copiesTotal;
            this.copiesAvailable = copiesTotal;
        }

        public void checkOut() {
            if (copiesAvailable > 0) {
                copiesAvailable--;
            }
        }

        public void checkIn() {
            if (copiesAvailable < copiesTotal) {
                copiesAvailable++;
            }
        }

        public int getCopiesAvailable() {
            return copiesAvailable;
        }
    }

    public static void main(String[] args) {
        BookInventory inventory = new BookInventory(3);

        inventory.checkOut();
        inventory.checkOut();
        inventory.checkOut();
        inventory.checkOut();

        System.out.println("Copies available after checkout: "
                + inventory.getCopiesAvailable());

        inventory.checkIn();
        inventory.checkIn();
        inventory.checkIn();
        inventory.checkIn();

        System.out.println("Copies available after check-in: "
                + inventory.getCopiesAvailable());
    }
}