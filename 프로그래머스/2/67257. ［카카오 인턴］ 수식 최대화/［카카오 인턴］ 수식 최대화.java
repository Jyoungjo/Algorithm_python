import java.util.*;

class Solution {
    final char[] operators = new char[]{'+', '-', '*'};
    boolean[] used = new boolean[3];
    long answer = Long.MIN_VALUE;
    
    public long solution(String expression) {
        for (int i = 0; i < 3; i++) {
            dfs(expression, i);
        }
        return answer;
    }
    
    private void dfs(String expr, int idx) {
        Deque<String> deque = new ArrayDeque<>();
        addExpr(deque, expr);
        if (isNumber(deque, idx)) {
            answer = Math.max(answer, Math.abs(Long.parseLong(deque.poll())));
            return;
        }
        String newExpr = makeNewExpr(deque);
        
        for (int i = 0; i < 3; i++) {
            if (used[i]) continue;
            used[i] = true;
            dfs(newExpr, i);
            used[i] = false;
        }
    }
    
    private void addExpr(Deque<String> deque, String expr) {
        int tmp = 0;
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) - '0' < 0 || expr.charAt(i) - '0' > 9) {
                if (i == 0 || (expr.charAt(i - 1) - '0' < 0 || expr.charAt(i - 1) - '0' > 9)) continue;
                String s = expr.substring(tmp, i);
                if (!s.isEmpty()) deque.add(s);
                deque.add(String.valueOf(expr.charAt(i)));
                tmp = i + 1;
            }
        }
        deque.add(expr.substring(tmp));
    }
    
    private boolean isNumber(Deque<String> deque, int idx) {
        int ops = deque.size() / 2;
        
        for (int i = 0; i < ops; i++) {
            String first = deque.poll();
            if (deque.peek().equals(String.valueOf(operators[idx]))) {
                String op = deque.poll(), second = deque.poll();
                String res;
                if (op.equals("+")) res = String.valueOf(Long.parseLong(first) + Long.parseLong(second));
                else if (op.equals("-")) res = String.valueOf(Long.parseLong(first) - Long.parseLong(second));
                else res = String.valueOf(Long.parseLong(first) * Long.parseLong(second));
                deque.addFirst(res);
            } else {
                deque.add(first);
                deque.add(deque.poll());
            }
        }
        deque.add(deque.poll());
        
        return deque.size() == 1;
    }
    
    private String makeNewExpr(Deque<String> deque) {
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.poll());
        }
        return sb.toString();
    }
}