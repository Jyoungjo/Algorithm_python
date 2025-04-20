import java.util.*;

class Solution {
    
    public int[] solution(int n, long k) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) numbers.add(i);

        long[] factorial = new long[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) factorial[i] = factorial[i - 1] * i;

        int[] result = new int[n];
        k--;

        for (int i = 0; i < n; i++) {
            int idx = (int)(k / factorial[n - 1 - i]);
            result[i] = numbers.get(idx);
            numbers.remove(idx);
            k %= factorial[n - 1 - i];
        }

        return result;
    }
}