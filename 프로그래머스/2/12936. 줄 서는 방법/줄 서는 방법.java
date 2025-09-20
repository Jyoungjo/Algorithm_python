import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int n, long k) {
        List<Integer> numbers = new LinkedList<>();
        for (int i = 1; i <= n; i++) numbers.add(i);
        long[] factorial = makeFactorial(n);
        
        k--;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int idx = (int) (k / factorial[n - 1 - i]);
            answer[i] = numbers.get(idx);
            numbers.remove(idx);
            k %= factorial[n - 1 - i];
        }
        
        return answer;
    }
    
    private long[] makeFactorial(int n) {
        long[] tmp = new long[n + 1];
        tmp[0] = 1;
        for (int i = 1; i <= n; i++) tmp[i] = tmp[i - 1] * i;
        return tmp;
    }
}