import java.util.*;

class Turn {
    boolean isWin;
    int cnt;
    
    public Turn(boolean isWin, int cnt) {
        this.isWin = isWin;
        this.cnt = cnt;
    }
}

class Solution {
    int[][] map;
    int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    int r, c, INF = Integer.MAX_VALUE;
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        map = board;
        r = board.length;
        c = board[0].length;
        Turn result = dfs(aloc, bloc, true, 0);
        return result.cnt;
    }
    
    // isMyTurn : true -> A턴, false -> B턴
    private Turn dfs(int[] aloc, int[] bloc, boolean isMyTurn, int cnt) {
        int ay = aloc[0], ax = aloc[1], by = bloc[0], bx = bloc[1];
        
        if ((map[ay][ax] == 0 && isMyTurn) || (map[by][bx] == 0 && !isMyTurn)) return new Turn(false, cnt);
        
        int y = isMyTurn ? ay : by, x = isMyTurn ? ax : bx;
        map[y][x] = 0;
        Turn result;
        boolean canMove = false;
        int win = INF, lose = -INF;
        
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i], nx = x + dx[i];
            if (!isWithinRange(ny, nx) || map[ny][nx] == 0) continue;
            canMove = true;
            result = isMyTurn ? dfs(new int[]{ny, nx}, bloc, !isMyTurn, cnt + 1) : dfs(aloc, new int[]{ny, nx}, !isMyTurn, cnt + 1);
            if (result.isWin) lose = Math.max(lose, result.cnt);
            else win = Math.min(win, result.cnt);
        }
        
        map[y][x] = 1;
        if (!canMove) return new Turn(false, cnt);
        if (win != INF) return new Turn(true, win);
        return new Turn(false, lose);
    }
    
    private boolean isWithinRange(int y, int x) {
        return 0 <= y && y < r && 0 <= x && x < c;
    }
}