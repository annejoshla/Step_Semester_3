public class Problem05_PackageDropOffLog {
    static abstract class DeliveryNote {
        public abstract String confirmDelivery();

        public String confirmDelivery(String signature) {
            return confirmDelivery() + ", signed by " + signature;
        }
    }

    static class ParcelNote extends DeliveryNote {
        private final String trackingId;

        public ParcelNote(String trackingId) {
            validateTrackingId(trackingId);
            this.trackingId = trackingId;
        }

        @Override
        public String confirmDelivery() {
            return "Parcel " + trackingId + " delivered";
        }
    }

    static class LetterNote extends DeliveryNote {
        private final String trackingId;

        public LetterNote(String trackingId) {
            validateTrackingId(trackingId);
            this.trackingId = trackingId;
        }

        @Override
        public String confirmDelivery() {
            return "Letter " + trackingId + " delivered";
        }
    }

    private static void validateTrackingId(String trackingId) {
        if (trackingId == null || trackingId.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Tracking ID cannot be blank.");
        }
    }

    public static void logAll(DeliveryNote[] notes) {
        for (DeliveryNote note : notes) {
            System.out.println(note.confirmDelivery());
        }
    }

    public static void main(String[] args) {
        ParcelNote parcel = new ParcelNote("TRK-1");

        System.out.println(parcel.confirmDelivery());
        System.out.println(parcel.confirmDelivery("J. Smith"));

        DeliveryNote reference = parcel;

        logAll(new DeliveryNote[]{
                reference,
                new LetterNote("TRK-2")
        });
    }
}