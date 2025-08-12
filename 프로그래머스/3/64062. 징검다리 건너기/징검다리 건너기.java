class Solution {
    public int solution(int[] stones, int k) {
        int l = 0, r = 200_000_000, answer = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            
            if (isOver(mid, k, stones)) {
                l = mid + 1;
                answer = mid;
            } else r = mid - 1;
        }
        
        return answer;
    }
    
    private boolean isOver(int people, int k, int[] stones) {
        int cnt = 0;
        for (int stone : stones) {
            if (stone - people < 0) cnt++;
            else cnt = 0;
            
            if (cnt == k) return false;
        }
        
        return true;
    }
}