import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        List<Integer> nums = Arrays.stream(array).boxed().collect(Collectors.toList());
        
        int[] answer = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {
            int[] command = commands[i];
            List<Integer> sub = new ArrayList<>(nums.subList(command[0] - 1, command[1]));
            Collections.sort(sub);
            answer[i] = sub.get(command[2] - 1);
        }
        
        return answer;
    }
}