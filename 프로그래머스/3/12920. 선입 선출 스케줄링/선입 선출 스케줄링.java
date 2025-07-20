class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
        if (n <= cores.length) return n;
        
        int l = 1, r = 10000 * n, time = 0;
        long job = 0;
        while (l <= r) {
            int mid = (l + r) / 2; // 모든 코어가 작업을 끝내는 시간
            
            long result = cal(cores, mid); // 모든 코어가 작업을 끝냈을 때 작업량
            if (result >= n) {
                job = result;
                time = mid;
                r = mid - 1;
            } else l = mid + 1;
        }
        
        System.out.printf("job: %d, time: %d\n", job, time);
        
        job -= n;
        for (int i = cores.length - 1; i >= 0; i--) {
            if (time % cores[i] == 0) {
                if (job == 0) {
                    answer = i + 1;
                    break;
                }
                job--;
            }
        }
        
        return answer;
    }
    
    private long cal(int[] cores, int time) {
        long result = cores.length;
        for (int c : cores) {
            result += (time / c);
        }
        return result;
    }
}