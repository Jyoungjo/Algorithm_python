import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        int[] days = new int[progresses.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < progresses.length; i++) {
            int day = (int) Math.ceil((100 - (double) progresses[i]) / (double) speeds[i]);
            days[i] = day;
        }

        for (int day : days) {
            if (answer.isEmpty() || stack.peek() < day) {
                stack.push(day);
                answer.add(1);
            } else {
                answer.set(answer.size() - 1, answer.get(answer.size() - 1) + 1);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}