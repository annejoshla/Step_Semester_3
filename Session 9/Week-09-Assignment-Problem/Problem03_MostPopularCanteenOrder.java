import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem03_MostPopularCanteenOrder {
    static class PopularItem {
        private final String item;
        private final int count;

        public PopularItem(String item, int count) {
            this.item = item;
            this.count = count;
        }

        @Override
        public String toString() {
            return "(\"" + item + "\", " + count + ")";
        }
    }

    public static PopularItem mostPopular(List<String> orders) {
        if (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException(
                    "Orders must contain at least one item.");
        }

        Map<String, Integer> counts = new HashMap<>();

        for (String item : orders) {
            counts.put(item, counts.getOrDefault(item, 0) + 1);
        }

        String bestItem = null;
        int bestCount = 0;

        // Scanning in original order preserves the first item on ties.
        for (String item : orders) {
            int count = counts.get(item);

            if (count > bestCount) {
                bestItem = item;
                bestCount = count;
            }
        }

        return new PopularItem(bestItem, bestCount);
    }

    public static void main(String[] args) {
        System.out.println(mostPopular(List.of(
                "dosa", "idli", "vada", "dosa",
                "idli", "dosa", "tea")));

        System.out.println(mostPopular(List.of(
                "tea", "coffee", "coffee", "tea")));
    }
}