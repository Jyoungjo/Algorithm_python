import java.util.*;

class Solution {
    int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    
    public int bfs(int[][] maps) {
        int w = maps[0].length, h = maps.length;
        boolean[][] visited = new boolean[h][w];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 1});
        visited[0][0] = true;
        
        while (!q.isEmpty()) {
            int[] coord = q.poll();
            int y = coord[0], x = coord[1], turn = coord[2];
            
            if (y == h - 1 && x == w - 1) return turn;
            
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                
                if (0 <= ny && ny < h && 0 <= nx && nx < w && !visited[ny][nx] && maps[ny][nx] == 1) {
                    q.offer(new int[]{ny, nx, turn + 1});
                    visited[ny][nx] = true;
                }
            }
        }
        
        return -1;
    }
    
    public int solution(int[][] maps) {
        return bfs(maps);
    }
}