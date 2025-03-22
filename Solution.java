
public class Solution {

    private static final int[] RANGE_OF_VALUES = {-1000, 1000};
    private static final int OFFSET_FOR_NEGATIVES = 1000;

    public int getLargestOutlier(int[] input) {
        int[] frequency = new int[1 + RANGE_OF_VALUES[1] + OFFSET_FOR_NEGATIVES];
        int totalSumValues = 0;
        for (int value : input) {
            ++frequency[value + OFFSET_FOR_NEGATIVES];
            totalSumValues += value;
        }
        return findMaxOutlier(totalSumValues, frequency);
    }

    private int findMaxOutlier(int totalSumValues, int[] frequency) {
        int maxOutlier = Integer.MIN_VALUE;
        for (int value = RANGE_OF_VALUES[1]; value >= RANGE_OF_VALUES[0]; --value) {
            if (frequency[value + OFFSET_FOR_NEGATIVES] == 0) {
                continue;
            }
            double candidate = ((double) totalSumValues - value) / 2;
            if (isValidCandidateForSpecialNumberThatIsNotOutlier(candidate, value, frequency)) {
                maxOutlier = value;
                break;
            }
        }
        return maxOutlier;
    }

    private boolean isValidCandidateForSpecialNumberThatIsNotOutlier(double candidate, int value, int[] frequency) {
        return candidate == (int) candidate
                && candidate >= RANGE_OF_VALUES[0]
                && candidate <= RANGE_OF_VALUES[1]
                && frequency[(int) candidate + OFFSET_FOR_NEGATIVES] > 0
                && (value != (int) candidate || frequency[value + OFFSET_FOR_NEGATIVES] > 1);
    }
}
