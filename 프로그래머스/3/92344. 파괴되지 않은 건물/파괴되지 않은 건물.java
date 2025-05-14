class Solution {
    int R, C;
    final int ENEMY = 1, OUR = 2;
    
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        R = board.length; C = board[0].length;
        int[][] damaged = new int[R + 1][C + 1];
        
        for (int[] s : skill) {
            int type = s[0], r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4], degree = s[5];
            
            if (type == ENEMY) {
                recordDamage(damaged, r1, c1, r2, c2, degree);
                continue;
            }
            
            recordDamage(damaged, r1, c1, r2, c2, -degree);
        }
        
        for (int i = 0; i <= R; i++) {
            for (int j = 0; j <= C; j++) {
                if (i > 0) damaged[i][j] += damaged[i - 1][j];
                if (j > 0) damaged[i][j] += damaged[i][j - 1];
                if (i > 0 && j > 0) damaged[i][j] -= damaged[i - 1][j - 1];
            }
        }
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                board[i][j] -= damaged[i][j];
                if (board[i][j] > 0) answer++;
            }
        }
        return answer;
    }
    
    private void recordDamage(int[][] damaged, int r1, int c1, int r2, int c2, int degree) {
        damaged[r1][c1] += degree;
        damaged[r1][c2 + 1] += -degree;
        damaged[r2 + 1][c1] += -degree;
        damaged[r2 + 1][c2 + 1] += degree;
    }
}