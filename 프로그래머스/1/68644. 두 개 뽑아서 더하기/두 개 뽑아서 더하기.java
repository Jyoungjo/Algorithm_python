import java.util.*;
import java.util.stream.*;

class Solution {
    Set<Integer> sums = new HashSet<>();
    
    public int[] solution(int[] numbers) {
        combinations(numbers, 0, 0, 0);
        List<Integer> answer = new ArrayList<>(sums);
        Collections.sort(answer);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private void combinations(int[] numbers, int mask, int r, int sum) {
        if (r == 2) {
            sums.add(sum);
            return;
        }
        
        for (int i = 0; i < numbers.length; i++) {
            if ((mask & (1 << i)) != 0) continue;
            
            combinations(numbers, (mask | (1 << i)), r + 1, sum + numbers[i]);
        }
    }
}