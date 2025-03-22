
/**
 * @param {number[]} input
 * @return {number}
 */
var getLargestOutlier = function (input) {
    this.RANGE_OF_VALUES = [-1000, 1000];
    this.OFFSET_FOR_NEGATIVES = 1000;

    const frequency = new Array(1 + this.RANGE_OF_VALUES[1] + this.OFFSET_FOR_NEGATIVES).fill(0);
    let totalSumValues = 0;
    for (let value of input) {
        ++frequency[value + this.OFFSET_FOR_NEGATIVES];
        totalSumValues += value;
    }
    return findMaxOutlier(totalSumValues, frequency);
};

/**
 * @param {number} totalSumValues
 * @param {number[]} frequency
 * @return {number}
 */
function findMaxOutlier(totalSumValues, frequency) {
    let maxOutlier = Number.MIN_SAFE_INTEGER;
    for (let value = this.RANGE_OF_VALUES[1]; value >= this.RANGE_OF_VALUES[0]; --value) {
        if (frequency[value + this.OFFSET_FOR_NEGATIVES] === 0) {
            continue;
        }
        const candidate = (totalSumValues - value) / 2;
        if (isValidCandidateForSpecialNumberThatIsNotOutlier(candidate, value, frequency)) {
            maxOutlier = value;
            break;
        }
    }
    return maxOutlier;
}

/**
 * @param {number} candidate
 * @param {number} value
 * @param {number[]} frequency
 * @return {boolean}
 */
function isValidCandidateForSpecialNumberThatIsNotOutlier(candidate, value, frequency) {
    return candidate === Math.floor(candidate)
            && candidate >= this.RANGE_OF_VALUES[0]
            && candidate <= this.RANGE_OF_VALUES[1]
            && frequency[candidate + this.OFFSET_FOR_NEGATIVES] > 0
            && (value !== candidate || frequency[value + this.OFFSET_FOR_NEGATIVES] > 1);
}
