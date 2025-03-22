
using System;

public class Solution
{
    private static readonly int[] RANGE_OF_VALUES = { -1000, 1000 };
    private static readonly int OFFSET_FOR_NEGATIVES = 1000;

    public int GetLargestOutlier(int[] input)
    {
        int[] frequency = new int[1 + RANGE_OF_VALUES[1] + OFFSET_FOR_NEGATIVES];
        int totalSumValues = 0;
        foreach (int value in input)
        {
            ++frequency[value + OFFSET_FOR_NEGATIVES];
            totalSumValues += value;
        }
        return FindMaxOutlier(totalSumValues, frequency);
    }

    private int FindMaxOutlier(int totalSumValues, int[] frequency)
    {
        int maxOutlier = int.MinValue;
        for (int value = RANGE_OF_VALUES[1]; value >= RANGE_OF_VALUES[0]; --value)
        {
            if (frequency[value + OFFSET_FOR_NEGATIVES] == 0)
            {
                continue;
            }
            double candidate = ((double)totalSumValues - value) / 2;
            if (IsValidCandidateForSpecialNumberThatIsNotOutlier(candidate, value, frequency))
            {
                maxOutlier = value;
                break;
            }
        }
        return maxOutlier;
    }

    private bool IsValidCandidateForSpecialNumberThatIsNotOutlier(double candidate, int value, int[] frequency)
    {
        return candidate == (int)candidate
                && candidate >= RANGE_OF_VALUES[0]
                && candidate <= RANGE_OF_VALUES[1]
                && frequency[(int)candidate + OFFSET_FOR_NEGATIVES] > 0
                && (value != (int)candidate || frequency[value + OFFSET_FOR_NEGATIVES] > 1);
    }
}
