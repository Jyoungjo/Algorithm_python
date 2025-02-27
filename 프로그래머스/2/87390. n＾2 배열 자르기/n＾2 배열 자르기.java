import java.util.*;

class Solution {
    public int[] solution(int n, long left, long right) {
        List<Integer> answer = new ArrayList<>();
        for (long i = left; i <= right; i++) {
            answer.add((int) Math.max(i / n, i % n) + 1);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}