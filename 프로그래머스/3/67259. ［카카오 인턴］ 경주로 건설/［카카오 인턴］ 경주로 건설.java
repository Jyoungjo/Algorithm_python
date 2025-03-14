import java.util.*;

class Solution {
    final int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    int[][] costs;
    int R, C;
    
    public int solution(int[][] board) {
        /*
            0 빈칸 1 벽 / 출발 (0,0) 도착 (N-1, N-1)
            직선 100원 코너 500원
            최소 비용 계산
        */
        R = board.length;
        C = board[0].length;
        costs = new int[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(costs[i], Integer.MAX_VALUE);
        }
        costs[0][0] = 0;
        dfs(new int[]{0, 0}, 0, -1, board);
        return costs[R - 1][C - 1];
    }
    
    private void dfs(int[] now, int cost, int dir, int[][] board) {
        int y = now[0], x = now[1];
        if (costs[y][x] < cost) return;
        
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d], nx = x + dx[d];
            if (!isWithinRange(ny, nx) || board[ny][nx] != 0) continue;
            if (dir == -1) {
                costs[ny][nx] = cost + 100;
                dfs(new int[]{ny, nx}, cost + 100, d, board);
            } else if (dir == 0 || dir == 2) {
                if (d == 1 || d == 3) {
                    if (costs[ny][nx] > cost + 600) costs[ny][nx] = cost + 600;
                    dfs(new int[]{ny, nx}, cost + 600, d, board);
                } else {
                    if (costs[ny][nx] > cost + 100) costs[ny][nx] = cost + 100;
                    dfs(new int[]{ny, nx}, cost + 100, d, board);
                }
            } else {
                if (d == 0 || d == 2) {
                    if (costs[ny][nx] > cost + 600) costs[ny][nx] = cost + 600;
                    dfs(new int[]{ny, nx}, cost + 600, d, board);
                } else {
                    if (costs[ny][nx] > cost + 100) costs[ny][nx] = cost + 100;
                    dfs(new int[]{ny, nx}, cost + 100, d, board);
                }
            }
        }
    }
    
    private boolean isWithinRange(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }
}