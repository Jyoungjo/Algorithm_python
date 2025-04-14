class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int start = 1;
        for (int station : stations) {
            int end = station - (w + 1);
            if (end < start) {
                start = station + w + 1;
                continue;
            }
            
            int len = end - start + 1;
            if (len <= 2 * w + 1) answer++;
            else answer += (int) Math.ceil((double) len / (2 * w + 1));
            
            start = station + w + 1;
        }
        
        if (start <= n) {
            int l = n - start + 1;
            if (l <= 2 * w + 1) answer++;
            else answer += (int) Math.ceil((double) l / (2 * w + 1));
        }
        
        return answer;
    }
}