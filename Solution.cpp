
#include <span>
#include <limits>
#include <vector>
using namespace std;

class Solution {

    static constexpr array<int, 2> RANGE_OF_VALUES = { -1000, 1000 };
    static const int OFFSET_FOR_NEGATIVES = 1000;

public:
    int getLargestOutlier(vector<int>& input) const {
        array<int, 1 + RANGE_OF_VALUES[1] + OFFSET_FOR_NEGATIVES> frequency{};
        int totalSumValues = 0;
        for (const auto& value : input) {
            ++frequency[value + OFFSET_FOR_NEGATIVES];
            totalSumValues += value;
        }
        return findMaxOutlier(totalSumValues, frequency);
    }

private:
    int findMaxOutlier(int totalSumValues, span<const int> frequency) const {
        int maxOutlier = numeric_limits<int>::min();
        for (int value = RANGE_OF_VALUES[1]; value >= RANGE_OF_VALUES[0]; --value) {
            if (frequency[value + OFFSET_FOR_NEGATIVES] == 0) {
                continue;
            }
            double candidate = (static_cast<double>(totalSumValues) - value) / 2;
            if (isValidCandidateForSpecialNumberThatIsNotOutlier(candidate, value, frequency)) {
                maxOutlier = value;
                break;
            }
        }
        return maxOutlier;
    }

    bool isValidCandidateForSpecialNumberThatIsNotOutlier(double candidate, int value, span<const int> frequency) const {
        return candidate == (int)candidate
                && candidate >= RANGE_OF_VALUES[0]
                && candidate <= RANGE_OF_VALUES[1]
                && frequency[(int)candidate + OFFSET_FOR_NEGATIVES] > 0
                && (value != (int)candidate || frequency[value + OFFSET_FOR_NEGATIVES] > 1);
    }
};
