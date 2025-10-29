import java.util.*;

class Solution {
    Deque<Integer> stack = new ArrayDeque<>();
    
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new LinkedList<>();
        for (int i = 0; i < speeds.length; i++) {
            int due = (int) Math.ceil((100 - (double) progresses[i]) / (double) speeds[i]);
            
            if (stack.isEmpty() || stack.peekLast() < due) {
                stack.addLast(due);
                answer.add(1);
                continue;
            }
            
            int tmp = answer.get(answer.size() - 1);
            answer.remove(answer.size() - 1);
            answer.add(tmp + 1);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}