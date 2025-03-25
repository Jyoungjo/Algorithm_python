import java.util.*;

class Solution {    
    public String solution(String p) {
        return dfs(p);
    }
    
    private String dfs(String p) {
        /*
            1. u, v로 분리
            2. 조건 체크
                2-1. u가 올바른 문자열 -> u + 재귀 v
                2-2. 올바르지 않음 -> '(' + v 재귀 문자열 + ')' + u의 첫번째,마지막 제거하고 뒤집은 문자열
        */
        if (p.isEmpty()) return "";
        String[] divided = divide(p);
        return check(divided[0]) ? divided[0] + dfs(divided[1]) : "(" + dfs(divided[1]) + ")" + reverse(divided[0]);
    }
    
    private String reverse(String u) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < u.length() - 1; i++) {
            char t = u.charAt(i) == '(' ? ')' : '(';
            sb.append(t);
        }
        return sb.toString();
    }
    
    private boolean check(String p) {
        Deque<Character> deque = new ArrayDeque<>();
        
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') deque.push('(');
            else {
                if (deque.isEmpty()) return false;
                else if (deque.peek() == '(') deque.pop();
            }
        }
        
        return true;
    }
    
    private String[] divide(String p) {
        int cnt = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') cnt++;
            else cnt--;
            
            if (cnt == 0) return new String[]{p.substring(0, i + 1), p.substring(i + 1)};
        }
        return new String[]{"", p};
    }
}