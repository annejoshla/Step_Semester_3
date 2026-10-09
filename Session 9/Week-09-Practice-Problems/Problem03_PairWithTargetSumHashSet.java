import java.util.HashSet;
import java.util.Set;

public class Problem03_PairWithTargetSumHashSet {
    public static boolean hasPairWithSum(
            int[] numbers,
            int target) {

        Set<Integer> seenNumbers = new HashSet<>();

        for (int number : numbers) {
            int complement = target - number;

            if (seenNumbers.contains(complement)) {
                return true;
            }

            seenNumbers.add(number);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] firstArray = {2, 7, 11, 15};
        int[] secondArray = {3, 4, 6};

        System.out.println(
                hasPairWithSum(firstArray, 9));

        System.out.println(
                hasPairWithSum(secondArray, 20));
    }
}