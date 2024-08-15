import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] result = new int[3];
        int[] one = {1, 2, 3, 4, 5};
        int[] two = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == one[i % one.length]) result[0]++;
            if (answers[i] == two[i % two.length]) result[1]++;
            if (answers[i] == three[i % three.length]) result[2]++;
        }
        
        int maxValue = Integer.MIN_VALUE;
        for (int v : result) {
            if (maxValue < v) {
                maxValue = v;
            }
        }
        
        List<Integer> answer = new ArrayList<>();
        
        for (int i = 0; i < result.length; i++) {
            if (maxValue == result[i]) {
                answer.add(i + 1);
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}