import java.util.*;

class Solution {
    public int[] solution(long begin, long end) {
        // 소수는 무조건 1, 나머지는 자기 자신을 제외한 가장 큰 값
        List<Integer> answer = new ArrayList<>();
        
        for (long i = begin; i <= end; i++) {
            if (i == 1) {
                answer.add(0);
                continue;
            }
            
            answer.add(cal(i));
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private int cal(long num) {
        int max = 1;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                max = i;
                if (num / i <= 10000000) return (int) num / i;
            }
        }
        return max;
    }
}