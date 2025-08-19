class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0, start = 1;
        for (int i = 0; i < stations.length; i++) {
            int end = stations[i] - w - 1;
            if (end < start) {
                start = stations[i] + w + 1;
                continue;
            }
            
            int dist = end - start + 1, width = 2 * w + 1;
            if (dist <= width) answer++;
            else answer += dist % width == 0 ? dist / width : dist / width + 1;
            
            start = stations[i] + w + 1;
        }
        
        if (start <= n) {
            int dist = n - start + 1, width = 2 * w + 1;
            if (dist <= width) answer++;
            else answer += dist % width == 0 ? dist / width : dist / width + 1;
        }
        
        return answer;
    }
}