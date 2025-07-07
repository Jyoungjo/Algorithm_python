class Solution {
    public int solution(int[] stones, int k) {
        int l = 1, r = 200000000, answer = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            
            if (isCross(stones, k, mid)) {
                answer = mid;
                l = mid + 1;
            } else r = mid - 1;
        }
        
        return answer;
    }
    
    private boolean isCross(int[] stones, int k, int people) {
        int cnt = 0;
        for (int stone : stones) {
            if (stone - people < 0) cnt++;
            else cnt = 0;
            
            if (cnt == k) return false;
        }
        return true;
    }
}