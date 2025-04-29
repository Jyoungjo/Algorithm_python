import java.util.*;

class Solution {
    int R, C;
    int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    boolean[][] visited;
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        R = m; C = n;
        visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !visited[i][j]) {
                    int[] result = bfs(new int[]{i, j}, picture, picture[i][j]);
                    numberOfArea += result[0];
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, result[1]);
                }
            }
        }
        
        return new int[]{numberOfArea, maxSizeOfOneArea};
    }
    
    private int[] bfs(int[] start, int[][] picture, int color) {
        int area = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        visited[start[0]][start[1]] = true;
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0], c = now[1];
            
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];
                
                if (!isRange(nr, nc)) continue;
                
                if (picture[nr][nc] == color && !visited[nr][nc]) {
                    area++;
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
        }
        
        return new int[]{1, area};
    }
    
    private boolean isRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}