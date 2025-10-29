import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();        
        
        for (int num : arr) {
            if (stack.isEmpty()) {
                stack.addLast(num);
                continue;
            }
            
            if (stack.peekLast() != num) stack.addLast(num);
        }
        
        return new ArrayList<>(stack).stream().mapToInt(Integer::intValue).toArray();
    }
}