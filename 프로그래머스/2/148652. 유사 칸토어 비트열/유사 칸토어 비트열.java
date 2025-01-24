import java.util.*;


class Solution {
    long answer = 0;
    
    public int solution(int n, long l, long r) {
        dnc(n, l, r, 0, 1);
        return (int) answer;
    }
    
    private void dnc(int n, long l, long r, int depth, long k) {
        long a = (long) Math.pow(5, n - depth);
        long end = k * a;
        long start = end - a + 1;
        
        if (l <= start && end <= r) {
            answer += Math.pow(4, n - depth);
            return;
        }
        
        if (start > r || end < l) return;
        
        long x = k * 5;
        dnc(n, l, r, depth + 1, x - 4);
        dnc(n, l, r, depth + 1, x - 3);
        dnc(n, l, r, depth + 1, x - 1);
        dnc(n, l, r, depth + 1, x);
    }
}