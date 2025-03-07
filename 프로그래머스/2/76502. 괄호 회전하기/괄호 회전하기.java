import java.util.*;

class Solution {    
    public int solution(String s) {
        int answer = 0;
        if (s.length() % 2 != 0) return 0;
        
        /*
            1. 체크
            2. 회전
        */
        
        for (int i = 0; i < s.length(); i++) {
            if (checkCorrect(s)) answer++;
            s = rotate(s);
        }
        return answer;
    }
    
    private String rotate(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.delete(0, 1);
        sb.append(s.charAt(0));
        return sb.toString();
    }
    
    private boolean checkCorrect(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
                continue;
            }
            
            char now = s.charAt(i);
            if (now == '[' || now == '{' || now == '(') stack.push(now);
            else if (now == ']' && stack.peek() == '[') stack.pop();
            else if (now == '}' && stack.peek() == '{') stack.pop();
            else if (now == ')' && stack.peek() == '(') stack.pop();
        }
        
        return stack.isEmpty();
    }
}