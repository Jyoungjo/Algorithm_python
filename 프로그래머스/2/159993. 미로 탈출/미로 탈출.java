import java.util.*;

class Solution {
    static int r, c;
    static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    
    public int solution(String[] maps) {
        r = maps.length;
        c = maps[0].length();
        
        int[][] points = findPoint(maps);
        int[] start = points[0], lever = points[1], end = points[2];
        
        int a1 = bfs(start, lever, maps), a2 = bfs(lever, end, maps);
        if (a1 == -1 || a2 == -1) return -1;
        return a1 + a2;
    }
    
    private int bfs(int[] start, int[] end, String[] maps) {
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        int[][] dist = new int[r][c];
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int y = now[0], x = now[1];
            
            if (y == end[0] && x == end[1]) return dist[y][x];
            
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                if (!isWithinRange(nx, ny) || dist[ny][nx] != 0 || maps[ny].charAt(nx) == 'X') continue;
                
                dist[ny][nx] = dist[y][x] + 1;
                q.add(new int[]{ny, nx});
            }
        }
        
        return -1;
    }
    
    private boolean isWithinRange(int x, int y) {
        return 0 <= y && y < r && 0 <= x && x < c;
    }
    
    private int[][] findPoint(String[] maps) {
        int[][] result = new int[3][2];
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (maps[i].charAt(j) == 'S') {
                    result[0][0] = i;
                    result[0][1] = j;
                }
                
                if (maps[i].charAt(j) == 'L') {
                    result[1][0] = i;
                    result[1][1] = j;
                }
                
                if (maps[i].charAt(j) == 'E') {
                    result[2][0] = i;
                    result[2][1] = j;
                }
            }
        }
        
        return result;
    }
}