public class Problem02_WarehouseLabelPrinter {
    interface Printable {
        String printLabel();
    }

    static class PackageBox implements Printable {
        private final String trackingId;

        public PackageBox(String trackingId) {
            if (trackingId == null || trackingId.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Tracking ID cannot be blank.");
            }

            this.trackingId = trackingId;
        }

        @Override
        public String printLabel() {
            return "Package label: " + trackingId;
        }
    }

    static class Invoice implements Printable {
        private final String invoiceNumber;

        public Invoice(String invoiceNumber) {
            if (invoiceNumber == null
                    || invoiceNumber.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Invoice number cannot be blank.");
            }

            this.invoiceNumber = invoiceNumber;
        }

        @Override
        public String printLabel() {
            return "Invoice label: " + invoiceNumber;
        }
    }

    public static void printAll(Printable[] items) {
        for (Printable item : items) {
            System.out.println(item.printLabel());
        }
    }

    public static void main(String[] args) {
        PackageBox packageBox = new PackageBox("TRK-88");
        Invoice invoice = new Invoice("INV-42");

        printAll(new Printable[]{packageBox, invoice});
    }
}