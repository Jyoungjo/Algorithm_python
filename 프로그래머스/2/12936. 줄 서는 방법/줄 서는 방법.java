import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        // 전체 개수 - n!
        int[] answer = new int[n];
        List<Integer> numbers = new LinkedList<>();
        for (int i = 1; i <= n; i++) numbers.add(i);
        
        long range = 1;
        for (int i = n; i >= 1; i--) range *= i;
        
        int num = n;
        k--;
        for (int i = 0; i < n; i++) {
            if (num > 0) range /= num--;
            int idx = (int) (k / range);
            answer[i] = numbers.get(idx);
            numbers.remove(idx);
            k %= range;
        }
        
        return answer;
    }
}