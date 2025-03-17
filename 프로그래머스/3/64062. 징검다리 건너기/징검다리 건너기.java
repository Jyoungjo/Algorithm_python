class Solution {
    public int solution(int[] stones, int k) {
        /*
            최적화 문제 -> 결정 문제
            최대 k만큼 건너 뛸 수 있을 때, 최대 몇 명까지 징검다리를 건널 수 있는가?
            -> m명은 최대 칸수 k일 때, 건널 수 있는가?
        */
        int l = 1, r = 200000000;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (isPossible(mid, stones, k)) l = mid + 1;
            else r = mid - 1;
        }
        return r;
    }
    
    private boolean isPossible(int people, int[] stones, int k) {
        int cnt = 0;
        for (int stone : stones) {
            if (stone - people < 0) cnt++;
            else cnt = 0;
            
            if (cnt == k) return false;
        }
        return true;
    }
}