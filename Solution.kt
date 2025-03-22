
import kotlin.math.floor

class Solution {

    private companion object {
        val RANGE_OF_VALUES = intArrayOf(-1000, 1000)
        const val OFFSET_FOR_NEGATIVES = 1000
    }

    fun getLargestOutlier(input: IntArray): Int {
        val frequency = IntArray(1 + RANGE_OF_VALUES[1] + OFFSET_FOR_NEGATIVES)
        var totalSumValues = 0
        for (value in input) {
            ++frequency[value + OFFSET_FOR_NEGATIVES]
            totalSumValues += value
        }
        return findMaxOutlier(totalSumValues, frequency)
    }

    private fun findMaxOutlier(totalSumValues: Int, frequency: IntArray): Int {
        var maxOutlier = Int.MIN_VALUE
        for (value in RANGE_OF_VALUES[1] downTo RANGE_OF_VALUES[0]) {
            if (frequency[value + OFFSET_FOR_NEGATIVES] == 0) {
                continue
            }
            val candidate: Double = (totalSumValues.toDouble() - value) / 2
            if (isValidCandidateForSpecialNumberThatIsNotOutlier(candidate, value, frequency)) {
                maxOutlier = value
                break
            }
        }
        return maxOutlier
    }

    private fun isValidCandidateForSpecialNumberThatIsNotOutlier(candidate: Double, value: Int, frequency: IntArray): Boolean {
        return candidate == floor(candidate)
                && candidate >= RANGE_OF_VALUES[0]
                && candidate <= RANGE_OF_VALUES[1]
                && frequency[candidate.toInt() + OFFSET_FOR_NEGATIVES] > 0
                && (value != candidate.toInt() || frequency[value + OFFSET_FOR_NEGATIVES] > 1)
    }
}
