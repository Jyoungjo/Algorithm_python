import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        if (n > s) return new int[]{-1};
        
        int num = s / n, remainder = s % n;
        Arrays.fill(answer, num);
        for (int i = n - remainder; i < n; i++) {
            answer[i] = num + 1;
        }
        
        return answer;
    }
}