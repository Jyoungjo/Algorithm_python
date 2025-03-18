import java.util.*;

class Solution {
    final int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    final int[] ady = {-1, 1, 1, -1}, adx = {1, 1, -1, -1};
    int R, C;
    
    public int solution(int[][] board) {
        R = board.length;
        C = board[0].length;
        return bfs(board, new int[]{0, 0, 0, 1, 0, 0});
    }
    
    private int bfs(int[][] board, int[] start) {
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        boolean[][][] visited = new boolean[R][C][2];
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int y1 = now[0], x1 = now[1], y2 = now[2], x2 = now[3], cnt = now[4], dir = now[5];
            
            // 좌표가 범위를 벗어난 경우
            if (isWithoutRange(y1, x1) || isWithoutRange(y2, x2)) continue;
            // 벽이 있는 경우
            if (board[y1][x1] == 1 || board[y2][x2] == 1) continue;
            // 이미 방문한 경우
            if (visited[y1][x1][dir] && visited[y2][x2][dir]) continue;
            
            if ((y1 == R - 1 && x1 == C - 1) || (y2 == R - 1 && x2 == C - 1)) return cnt;
            
            visited[y1][x1][dir] = true;
            visited[y2][x2][dir] = true;
            
            // 정방향 이동 (상하좌우)
            move(q, board, visited, new int[]{y1, x1}, new int[]{y2, x2}, dir, cnt);
            
            // 회전 (축 기준 90도 회전)
            rotate(q, board, visited, new int[]{y1, x1}, new int[]{y2, x2}, dir, cnt);
        }
        
        return 0;
    }
    
    private void rotate(Queue<int[]> q, int[][] board, boolean[][][] visited, int[] c1, int[] c2, int dir, int cnt) {
        int y1 = c1[0], x1 = c1[1], y2 = c2[0], x2 = c2[1];
        
        if (dir == 1) {
            if (x1 - 1 >= 0 && board[y1][x1 - 1] == 0 && board[y2][x2 - 1] == 0) {
                q.add(new int[]{y1, x1, y1, x2 - 1, cnt + 1, 0});
                q.add(new int[]{y2, x1 - 1, y2, x2, cnt + 1, 0});
            }
            if (x1 + 1 < C && board[y1][x1 + 1] == 0 && board[y2][x2 + 1] == 0) {
                q.add(new int[]{y1, x1, y1, x2 + 1, cnt + 1, 0});
                q.add(new int[]{y2, x1 + 1, y2, x2, cnt + 1, 0});
            }
        } else {
            if (y1 - 1 >= 0 && board[y1 - 1][x1] == 0 && board[y2 - 1][x2] == 0) {
                q.add(new int[]{y1, x1, y2 - 1, x1, cnt + 1, 1});
                q.add(new int[]{y1 - 1, x2, y2, x2, cnt + 1, 1});
            }
            if (y1 + 1 < R && board[y1 + 1][x1] == 0 && board[y2 + 1][x2] == 0) {
                q.add(new int[]{y1, x1, y2 + 1, x1, cnt + 1, 1});
                q.add(new int[]{y1 + 1, x2, y2, x2, cnt + 1, 1});
            }
        }
    }
    
    private void move(Queue<int[]> q, int[][] board, boolean[][][] visited, int[] c1, int[] c2, int dir, int cnt) {
        int y1 = c1[0], x1 = c1[1], y2 = c2[0], x2 = c2[1];
        
        for (int d = 0; d < 4; d++) {
            int ny1 = y1 + dy[d], nx1 = x1 + dx[d], ny2 = y2 + dy[d], nx2 = x2 + dx[d];
            q.add(new int[]{ny1, nx1, ny2, nx2, cnt + 1, dir});
        }
    }
    
    private boolean isWithoutRange(int y, int x) {
        return y < 0 || y >= R || x < 0 || x >= C;
    }
}