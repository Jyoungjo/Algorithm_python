import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        // 얼마만큼의 시간 동안 n개의 작업을 처리할 수 있는지 확인
        // 전체 시간을 구했다면 작업 수만큼 코어를 순회하면서 마지막 작업 찾기
        int l = 1, r = n * 10000, time = 0;
        long job = 0;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            
            long result = process(cores, mid);
            if (result >= n) {
                r = mid - 1;
                time = mid;
                job = result;
            } else l = mid + 1;
        }
        
        // 처리해야 될 작업의 개수는 n이지만 time 동안 처리한 작업량 job은 n보다 클 수 있음.
        // 우리가 구해야 할 것은 job을 처리하는 동안 n의 처리량을 어떤 코어에서 처리하는지를 확인하는 것이므로 job -= n 을 통해 잔여 작업량을 구함
        // 이후 코어의 역순회를 진행하면서 잔여 작업을 어떤 코어에서 처리했는지를 확인
        // 찾은 코어의 idx 에서 job == 0일 때, time % cores[i] == 0 이라면 해당 idx의 코어가 n만큼의 작업을 처리한 것이므로 i + 1 리턴
        job -= n;
        for (int i = cores.length - 1; i >= 0; i--) {
            if (time % cores[i] == 0) {
                if (job == 0) return i + 1;
                job--;
            } 
        }
        
        return 0;
    }
    
    private long process(int[] cores, int time) {
        long tmp = cores.length;
        for (int core : cores) tmp += time / core;
        
        return tmp;
    }
}