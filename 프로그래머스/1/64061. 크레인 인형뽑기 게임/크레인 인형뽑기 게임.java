import java.util.*;

class Solution {
    Deque<Integer> stack = new ArrayDeque<>();
    
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        for (int target : moves) {
            int now = pull(board, target - 1);
            if (now == -1) continue;
            
            if (stack.isEmpty()) {
                stack.addLast(now);
                continue;
            }
            
            if (stack.peekLast() == now) {
                stack.removeLast();
                answer += 2;
            } else stack.addLast(now);
        }
        
        return answer;
    }
    
    private int pull(int[][] board, int target) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][target] != 0) {
                int result = board[i][target];
                board[i][target] = 0;
                return result;
            }
        }
        
        return -1;
    }
}