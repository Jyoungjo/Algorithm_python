import java.util.*;

class Solution {
    int r, c;
    int answer = Integer.MAX_VALUE;
    int[] dr = {0, -1, 0, 1, 0}, dc = {0, 0, 1, 0, -1};
    
    public int solution(int[][] clockHands) {
        r = clockHands.length;
        c = clockHands[0].length;
        int[] firstRow = new int[c];
        dfs(clockHands, firstRow, 0);
        return answer;
    }
    
    private void dfs(int[][] clockHands, int[] firstRow, int depth) {
        if (depth == r) {
            int[][] newClock = copyArray(clockHands, firstRow);
            int cnt = solvePuzzle(newClock);
            if (isAligned(newClock)) answer = Math.min(cnt, answer);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            firstRow[depth] = i;
            dfs(clockHands, firstRow, depth + 1);
        }
    }
    
    private int[][] copyArray(int[][] original, int[] firstRow) {
        int[][] arr = new int[r + 1][c];
        Arrays.setAll(arr[0], col -> firstRow[col]);
        for (int i = 1; i <= r; i++) {
            int row = i - 1;
            Arrays.setAll(arr[i], col -> original[row][col]);
        }
        return arr;
    }
    
    private int solvePuzzle(int[][] puzzle) {
        int result = 0;
        for (int i = 1; i <= r; i++) {
            for (int j = 0; j < c; j++) {
                int cnt = (4 - puzzle[i - 1][j]) % 4;
                if (cnt != 0) {
                    result += cnt;
                    rotate(puzzle, i, j, cnt);
                }
            }
        }
        return result;
    }
    
    private void rotate(int[][] puzzle, int row, int col, int cnt) {
        Set<int[]> coord = getDirection(puzzle, row, col);
        for (int[] co : coord) {
            puzzle[co[0]][co[1]] = (puzzle[co[0]][co[1]] + cnt) % 4;
        }
    }
    
    private Set<int[]> getDirection(int[][] puzzle, int row, int col) {
        Set<int[]> set = new HashSet<>();
        
        for (int i = 0; i < 5; i++) {
            int nr = row + dr[i], nc = col + dc[i];
            if (isWithinRange(nr, nc)) set.add(new int[]{nr, nc});
        }
        
        return set;
    }
    
    private boolean isWithinRange(int row, int col) {
        return 0 <= row && row < r + 1 && 0 <= col && col < c;
    }
    
    private boolean isAligned(int[][] puzzle) {
        for (int i = 1; i <= r; i++) {
            for (int j = 0; j < c; j++) {
                if (puzzle[i][j] != 0) return false;
            }
        }
        return true;
    }
}