import java.util.*;

class Triangle {
    int y, x, state;
    
    Triangle(int y, int x, int state) {
        this.y = y;
        this.x = x;
        this.state = state;
    }
}

class Solution {
    final int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    final int[][] dir = {{2, 3}, {0, 3}, {0, 1}, {1, 2}};
    final int[][] ud = {{1, 0}, {0, 1}, {0, 1}, {1, 0}};
    int R, C;
    int[][] GRID;
    int[][][] group;
    
    public int solution(int[][] grid) {        
        int answer = -1;
        R = grid.length; C = grid[0].length;
        GRID = grid;
        group = new int[R][C][2];
        int id = 1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for (int k = 0; k < 2; k++) {
                    if (group[i][j][k] == 0) {
                        answer = Math.max(answer, bfs(i, j, k, id));
                        id++;
                    }
                }
            }
        }
        
        return answer;
    }
    
    private int bfs(int y, int x, int state, int id) {
        Queue<Triangle> q = new LinkedList<>();
        q.add(new Triangle(y, x, state));
        group[y][x][state] = id;
        
        int cnt = 0;
        while (!q.isEmpty()) {
            Triangle now = q.poll();
            cnt++;
            
            int shape;
            if (GRID[now.y][now.x] == -1) shape = now.state == 0 ? 0 : 2;
            else shape = now.state == 0 ? 1 : 3;
            
            for (int d = 0; d < 2; d++) {
                int nd = dir[shape][d], ny = now.y + dy[nd], nx = now.x + dx[nd];
                
                if (!isInRange(ny, nx)) continue;
                if (group[ny][nx][0] == id || group[ny][nx][1] == id) continue;
                
                int nState;
                if (nd == 0 || nd == 2) nState = ud[shape][GRID[ny][nx] == -1 ? 0 : 1];
                else nState = nd == 1 ? 0 : 1;
                
                group[ny][nx][nState] = id;
                q.add(new Triangle(ny, nx, nState));
            }
        }
        
        return cnt;
    }
    
    private boolean isInRange(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }
}