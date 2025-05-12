import java.util.*;

class Solution {
    int INF = 987654321;
    int r, c;
    int answer;
    
    public int solution(int[][] clockHands) {
        /*
            퍼즐은 내가 움직이려는 칸 바로 윗칸의 영향을 받는다.
            하지만 맨 윗줄의 경우 그보다 윗 줄이 없기 때문에 윗줄을 하나 더 추가한 배열을 만들어 경우의 수를 탐색한다.
        */
        answer = INF;
        r = clockHands.length; c = clockHands[0].length;
        
        int[] firstRow = new int[c];
        dfs(clockHands, firstRow, 0);
        return answer;
    }
    
    private void dfs(int[][] clockHands, int[] firstRow, int depth) {
        if (depth == r) {
            int[][] newClock = copy(clockHands, firstRow);
            answer = Math.min(answer, solve(newClock));
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            firstRow[depth] = i;
            dfs(clockHands, firstRow, depth + 1);
        }
    }
    
    private int[][] copy(int[][] original, int[] first) {
        int[][] copied = new int[r + 1][c];
        Arrays.setAll(copied[0], col -> first[col]);
        for (int i = 1; i <= r; i++) {
            int row = i - 1;
            Arrays.setAll(copied[i], col -> original[row][col]);
        }
        return copied;
    }
    
    private int solve(int[][] clocks) {
        int cnt = 0;
        for (int row = 1; row <= r; row++) {
            for (int col = 0; col < c; col++) {
                int rotCnt = (4 - clocks[row - 1][col]) % 4;
                if (rotCnt > 0) {
                    rotate(clocks, row, col, rotCnt);
                    cnt += rotCnt;
                }
            }
        }
        
        if (allAlign(clocks)) return cnt;
        return INF;
    }
    
    private void rotate(int[][] clocks, int row, int col, int cnt) {
        if (row - 1 >= 0) clocks[row - 1][col] = (clocks[row - 1][col] + cnt) % 4;
        if (row + 1 <= r) clocks[row + 1][col] = (clocks[row + 1][col] + cnt) % 4;
        if (col - 1 >= 0) clocks[row][col - 1] = (clocks[row][col - 1] + cnt) % 4;
        if (col + 1 < c) clocks[row][col + 1] = (clocks[row][col + 1] + cnt) % 4;
        clocks[row][col] = (clocks[row][col] + cnt) % 4;
    }
    
    private boolean allAlign(int[][] clocks) {
        for (int i = 1; i <= r; i++) {
            for (int j = 0; j < c; j++) {
                if (clocks[i][j] != 0) return false;
            }
        }
        
        return true;
    }
}