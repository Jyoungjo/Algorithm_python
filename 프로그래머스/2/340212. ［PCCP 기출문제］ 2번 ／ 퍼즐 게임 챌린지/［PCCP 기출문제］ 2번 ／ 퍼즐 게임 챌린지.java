class Solution {
    public long solution(int[] diffs, int[] times, long limit) {
        int n = diffs.length;
        long answer = 0, l = 1, r = limit;
        while (l <= r) {
            long result = 0, mid = (l + r) / 2;
            
            for (int i = 0; i < n; i++) {
                result += times[i];
                if (diffs[i] > mid) {
                    long wrongCnt = diffs[i] - mid;
                    result += (times[i] + times[i - 1]) * wrongCnt;
                }
            }
            
            if (result <= limit) {
                answer = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return answer;
    }
}