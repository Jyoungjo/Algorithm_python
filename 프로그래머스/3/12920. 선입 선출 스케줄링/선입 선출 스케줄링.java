import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
        if (n <= cores.length) return n;
        
        int l = 1, r = 10000 * n; // 기준: 작업 처리 시간
        int time = 0; long job = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            
            //작업 처리 시간 동안 처리 가능한 일의 양
            long result = cal(mid, cores);
            if (result >= n) {
                r = mid - 1;
                time = mid;
                job = result;
            }
            else l = mid + 1;
        }
        
        job -= n;
        for (int i = cores.length - 1; i >= 0; i--) {
            if (time % cores[i] == 0) {
                if (job == 0) return i + 1;
                job--;
            }
        }
        
        return answer;
    }
    
    private long cal(int time, int[] cores) {
        long job = cores.length;
        for (int c : cores) {
            job += (time / c);
        }
        return job;
    }
}