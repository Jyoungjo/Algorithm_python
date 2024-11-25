import java.util.*;


class Solution {
    public int[] solution(long n) {
        List<Long> result = new ArrayList<>();
        
        while (n != 0) {
            result.add(n % 10);
            n /= 10;
        }
        
        return result.stream().mapToInt(Long::intValue).toArray();
    }
}