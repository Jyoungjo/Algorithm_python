import java.util.*;


class Solution {
    int r, c;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] beginning, int[][] target) {
        r = beginning.length;
        c = beginning[0].length;
        dfs(beginning, target, 0, 0, 0);
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    private void dfs(int[][] beginning, int[][] target, int row, int col, int res) {        
        if (row == r) {
            if (col == c) {
                if (checkEqual(beginning, target)) answer = Math.min(answer, res);
                return;
            }
            
            flipCol(beginning, col);
            dfs(beginning, target, row, col + 1, res + 1);
            flipCol(beginning, col);
            dfs(beginning, target, row, col + 1, res);
            
            return;
        }
        
        flipRow(beginning, row);
        dfs(beginning, target, row + 1, col, res + 1);
        flipRow(beginning, row);
        dfs(beginning, target, row + 1, col, res);
    }
    
    private boolean checkEqual(int[][] beginning, int[][] target) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (beginning[i][j] != target[i][j]) return false;
            }
        }
        return true;
    }
    
    private void flipRow(int[][] board, int row) {
        for (int i = 0; i < c; i++) {
            board[row][i] ^= 1;
        }
    }
    
    private void flipCol(int[][] board, int col) {
        for (int i = 0; i < r; i++) {
            board[i][col] ^= 1;
        }
    }
}