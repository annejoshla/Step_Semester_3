import java.util.Arrays;

class Problem04_PairWithTargetSumSorting {
    public static boolean hasPairWithSum(
            int[] numbers,
            int target) {

        int[] sortedNumbers = Arrays.copyOf(
                numbers,
                numbers.length);

        Arrays.sort(sortedNumbers);

        int left = 0;
        int right = sortedNumbers.length - 1;

        while (left < right) {
            long sum = (long) sortedNumbers[left]
                    + sortedNumbers[right];

            if (sum == target) {
                return true;
            }

            if (sum < target) {
                left++;
            } else {
                right--;
            }
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