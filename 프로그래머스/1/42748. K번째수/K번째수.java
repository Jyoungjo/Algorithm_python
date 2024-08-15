import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        List<Integer> answer = new ArrayList<>();
        for (int[] command : commands) {
            int[] tmp = Arrays.copyOfRange(array, command[0] - 1, command[1]);
            Arrays.sort(tmp);
            answer.add(tmp[command[2] - 1]);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}