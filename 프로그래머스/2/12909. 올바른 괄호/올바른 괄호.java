import java.util.*;

class Solution {
    boolean solution(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ')') {
                if (stack.isEmpty()) return false;
                if (stack.peekLast() == '(') stack.removeLast();
            } else stack.addLast(c[i]);
        }
        
        return stack.isEmpty();
    }
}