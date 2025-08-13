import java.util.*;

class Solution {
    boolean[][][] visited;
    int[][] BOARD;
    final int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    int R, C;
    
    public int solution(int[][] board) {
        BOARD = board;
        R = board.length; C = board[0].length;
        visited = new boolean[R][C][2];
        return bfs(new int[]{0, 0, 0, 1, 0, 0});
    }
    
    private int bfs(int[] start) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(start);
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int y1 = now[0], x1 = now[1], y2 = now[2], x2 = now[3], mv = now[4], dir = now[5];
            
            if (!isRange(y1, x1) || !isRange(y2, x2)) continue;
            if (BOARD[y1][x1] == 1 || BOARD[y2][x2] == 1) continue;
            if (visited[y1][x1][dir] && visited[y2][x2][dir]) continue;
            
            if ((y1 == R - 1 && x1 == C - 1) || (y2 == R - 1 && x2 == C - 1)) return mv;
            
            visited[y1][x1][dir] = true;
            visited[y2][x2][dir] = true;
            
            move(y1, x1, y2, x2, mv, dir, q);
            rotate(y1, x1, y2, x2, mv, dir, q);
        }
        
        return -1;
    }
    
    private void move(int y1, int x1, int y2, int x2, int mv, int dir, Queue<int[]> q) {
        for (int d = 0; d < 4; d++) {
            int ny1 = y1 + dy[d], nx1 = x1 + dx[d], ny2 = y2 + dy[d], nx2 = x2 + dx[d];
            q.add(new int[]{ny1, nx1, ny2, nx2, mv + 1, dir});
        }
    }
    
    private void rotate(int y1, int x1, int y2, int x2, int mv, int dir, Queue<int[]> q) {
        if (dir == 0) {
            if (y1 - 1 >= 0 && BOARD[y1 - 1][x1] != 1 && BOARD[y1 - 1][x2] != 1) {
                q.add(new int[]{y1, x1, y1 - 1, x1, mv + 1, 1});
                q.add(new int[]{y2 - 1, x2, y2, x2, mv + 1, 1});
            }
            if (y1 + 1 < R && BOARD[y1 + 1][x1] != 1 && BOARD[y1 + 1][x2] != 1) {
                q.add(new int[]{y1, x1, y1 + 1, x1, mv + 1, 1});
                q.add(new int[]{y2 + 1, x2, y2, x2, mv + 1, 1});
            }
        } else {
            if (x1 - 1 >= 0 && BOARD[y1][x1 - 1] != 1 && BOARD[y2][x1 - 1] != 1) {
                q.add(new int[]{y1, x1, y1, x1 - 1, mv + 1, 0});
                q.add(new int[]{y2, x1 - 1, y2, x2, mv + 1, 0});
            }
            if (x1 + 1 < C && BOARD[y1][x1 + 1] != 1 && BOARD[y2][x1 + 1] != 1) {
                q.add(new int[]{y2, x1 + 1, y2, x2, mv + 1, 0});
                q.add(new int[]{y1, x1, y1, x2 + 1, mv + 1, 0});
            }
        }
    }
    
    private boolean isRange(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }
}