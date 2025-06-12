class Solution {
    public int solution(int n, int[] cores) {
        // 작업 처리 시간 = 최대 50000 * 10000
        // 특정 시간에 따라 누적 작업량이 단조 증가 -> 이분 탐색 + 스케줄링
        
        if (n <= cores.length) return n;
        
        int l = 1, r = n * 10000;
        int time = 0; long work = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            
            long result = cal(mid, cores);
            if (result >= n) {
                time = mid;
                r = mid - 1;
                work = result;
            } else l = mid + 1;
        }
        
        work -= n;
        for (int i = cores.length - 1; i >= 0; i--) {
            if (time % cores[i] == 0) {
                if (work == 0) return i + 1;
                work--;
            }
        }
        
        return -1;
    }
    
    private long cal(int time, int[] cores) {
        long res = cores.length;
        for (int core : cores) {
            res += (time / core);
        }
        return res;
    }
}