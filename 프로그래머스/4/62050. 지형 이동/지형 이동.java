import java.util.*;

class Island implements Comparable<Island> {
    int y, x, cost;
    
    Island(int y, int x, int cost) {
        this.y = y;
        this.x = x;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Island o) {
        return this.cost - o.cost;
    }
}

class Solution {
    final int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    int N, H;
    int[][] LAND;
    
    public int solution(int[][] land, int height) {
        init(land, height);
        return solve();
    }
    
    private int solve() {
        Queue<Island> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];
        pq.add(new Island(0, 0, 0));
        
        int tot = 0;
        while (!pq.isEmpty()) {
            Island now = pq.poll();
            
            if (visited[now.y][now.x]) continue;
            
            tot += now.cost;
            visited[now.y][now.x] = true;
            
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d], nx = now.x + dx[d];
                
                if (!isInRange(ny, nx)) continue;
                
                int gap = Math.abs(LAND[ny][nx] - LAND[now.y][now.x]);
                if (gap <= H) pq.add(new Island(ny, nx, 0));
                else pq.add(new Island(ny, nx, gap));
            }
        }
        
        return tot;
    }
    
    private boolean isInRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
    
    private void init(int[][] land, int height) {
        N = land.length;
        LAND = land;
        H = height;
    }
}