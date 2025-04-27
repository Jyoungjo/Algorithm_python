import java.util.*;

class Solution {
    int answer = 0;
    int[] board;
    
    public int solution(int n) {
        board = new int[n];
        backtracking(n, 0);
        return answer;
    }
    
    private void backtracking(int n, int row) {
        if (row == n) {
            answer++;
            return;
        }
        
        for (int col = 0; col < n; col++) {
            board[row] = col;
            if (isPossible(row)) backtracking(n, row + 1);
        }
    }
    
    private boolean isPossible(int row) {
        for (int i = 0; i < row; i++) {
            if (board[row] == board[i] || 
                Math.abs(row - i) == Math.abs(board[row] - board[i])) return false;
        }
        
        return true;
    }
}