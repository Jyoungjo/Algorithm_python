import java.util.*;

class Solution {
    public int[] solution(long begin, long end) {
        List<Integer> answer = new ArrayList<>();
        for (long num = begin; num <= end; num++) {
            if (num == 1) {
                answer.add(0);
                continue;
            }
            
            int max = 1;
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    max = i;
                    if (num / i <= 10_000_000) {
                        max = (int) num / i;
                        break;
                    }
                }
            }
            answer.add(max);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}