public class Problem04_WarehouseInventory {
    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        if (sectionA.length != sectionB.length) {
            System.out.println("Both sections must have the same number of items.");
            return;
        }

        int sectionATotal = 0;
        int sectionBTotal = 0;

        int highestQuantity = sectionA[0];
        String highestSection = "Section A";
        int highestItemNumber = 1;

        for (int i = 0; i < sectionA.length; i++) {
            sectionATotal += sectionA[i];
            sectionBTotal += sectionB[i];

            if (sectionA[i] > highestQuantity) {
                highestQuantity = sectionA[i];
                highestSection = "Section A";
                highestItemNumber = i + 1;
            }

            if (sectionB[i] > highestQuantity) {
                highestQuantity = sectionB[i];
                highestSection = "Section B";
                highestItemNumber = i + 1;
            }
        }

        String status;

        if (sectionATotal == sectionBTotal) {
            status = "Balanced";
        } else {
            status = "Not Balanced";
        }

        System.out.println("Section A Total: " + sectionATotal
                + " | Section B Total: " + sectionBTotal
                + " | Status: " + status
                + " | Highest Quantity: " + highestQuantity
                + " (" + highestSection
                + ", Item " + highestItemNumber + ")");
    }

    public static void main(String[] args) {
        int[] sectionA = {20, 15, 30};
        int[] sectionB = {25, 10, 30};

        analyzeInventory(sectionA, sectionB);
    }
}