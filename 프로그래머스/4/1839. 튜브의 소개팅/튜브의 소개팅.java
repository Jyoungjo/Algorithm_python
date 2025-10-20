import java.util.*;

class Node {
    int y, x, cnt;
    
    Node(int y, int x, int cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}

class Solution {
    final int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    int[][] TIME_MAP;
    int R, C;
    
    public int[] solution(int m, int n, int s, int[][] time_map) {
        R = m; C = n;
        TIME_MAP = time_map;
        return bfs(s);
    }
    
    private int[] bfs(int s) {
        int[] result = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        
        Queue<Node> q = new LinkedList<>();
        long[][] dist = new long[R][C];
        for (int i = 0; i < R; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        q.add(new Node(0, 0, 0));
        dist[0][0] = 0;
        
        while (!q.isEmpty()) {
            Node now = q.poll();
            
            if (now.y == R - 1 && now.x == C - 1) {
                if (result[0] >= now.cnt) {
                    result[0] = now.cnt;
                    result[1] = Math.min(result[1], (int) dist[now.y][now.x]);
                }
                continue;
            }
            
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d], nx = now.x + dx[d];
                
                if (!isInRange(ny, nx) || TIME_MAP[ny][nx] == -1) continue;
                
                if (dist[ny][nx] > dist[now.y][now.x] + TIME_MAP[ny][nx]) {
                    if (dist[now.y][now.x] + TIME_MAP[ny][nx] > s) continue;
                    
                    dist[ny][nx] = dist[now.y][now.x] + TIME_MAP[ny][nx];
                    q.add(new Node(ny, nx, now.cnt + 1));
                }
            }
        }
        
        return result;
    }
    
    private boolean isInRange(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }
}