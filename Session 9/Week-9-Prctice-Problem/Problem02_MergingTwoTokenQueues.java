import java.util.ArrayList;
import java.util.List;

public class Problem02_MergingTwoTokenQueues {
    public static List<Integer> mergeTokens(
            int[] counterA,
            int[] counterB) {

        List<Integer> merged =
                new ArrayList<>(counterA.length + counterB.length);

        int pointerA = 0;
        int pointerB = 0;

        while (pointerA < counterA.length
                && pointerB < counterB.length) {

            if (counterA[pointerA] <= counterB[pointerB]) {
                merged.add(counterA[pointerA]);
                pointerA++;
            } else {
                merged.add(counterB[pointerB]);
                pointerB++;
            }
        }

        while (pointerA < counterA.length) {
            merged.add(counterA[pointerA]);
            pointerA++;
        }

        while (pointerB < counterB.length) {
            merged.add(counterB[pointerB]);
            pointerB++;
        }

        return merged;
    }

    public static void main(String[] args) {
        int[] counterA = {3, 8, 15, 20};
        int[] counterB = {5, 8, 12};

        System.out.println(
                mergeTokens(counterA, counterB));

        System.out.println(
                mergeTokens(new int[]{}, new int[]{4, 9}));
    }
}