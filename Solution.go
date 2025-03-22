
package main
import "math"

var RANGE_OF_VALUES = [2]int{-1000, 1000}
const OFFSET_FOR_NEGATIVES = 1000

func getLargestOutlier(input []int) int {
    frequency := make([]int, 1 + RANGE_OF_VALUES[1] + OFFSET_FOR_NEGATIVES)
    var totalSumValues = 0
    for _, value := range input {
        frequency[value + OFFSET_FOR_NEGATIVES]++
        totalSumValues += value
    }
    return findMaxOutlier(totalSumValues, frequency)
}

func findMaxOutlier(totalSumValues int, frequency []int) int {
    var maxOutlier = math.MinInt
    for value := RANGE_OF_VALUES[1]; value >= RANGE_OF_VALUES[0]; value-- {
        if frequency[value + OFFSET_FOR_NEGATIVES] == 0 {
            continue
        }
        candidate := float64(totalSumValues - value) / 2
        if isValidCandidateForSpecialNumberThatIsNotOutlier(candidate, value, frequency) {
            maxOutlier = value
            break
        }
    }
    return maxOutlier
}

func isValidCandidateForSpecialNumberThatIsNotOutlier(candidate float64, value int, frequency []int) bool {
    return candidate == math.Floor(candidate) &&
        int(candidate) >= RANGE_OF_VALUES[0] &&
        int(candidate) <= RANGE_OF_VALUES[1] &&
        frequency[int(candidate) + OFFSET_FOR_NEGATIVES] > 0 &&
        (value != int(candidate) || frequency[value + OFFSET_FOR_NEGATIVES] > 1)
}
