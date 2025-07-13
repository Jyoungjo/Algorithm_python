class Solution {
    public int solution(int n, int[] stations, int w) {
        int left = 1;
        int answer = 0;
        
        for (int st : stations) {
            int right = st - w - 1;
            if (right < left) {
                left = st + w + 1;
                continue;
            }
            
            int range = 2 * w + 1, len = right - left + 1;
            answer += addTower(len, range);
            
            left = st + w + 1;
        }
        
        if (n >= left) {
            int range = 2 * w + 1, len = n - left + 1;
            answer += addTower(len, range);
        }
        
        return answer;
    }
    
    private int addTower(int len, int range) {
        if (len <= range) return 1;
        else {
            double res = len / range;
            return len % range > 0 ? (int) res + 1 : (int) res;
        }
    }
}