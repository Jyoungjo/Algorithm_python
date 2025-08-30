import java.util.*;

class Car {
    int y, x, dir, cost;
    
    Car(int y, int x, int dir, int cost) {
        this.y = y;
        this.x = x;
        this.dir = dir;
        this.cost = cost;
    }
}

class Solution {
    final int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    int N;
    int[][][] dp;
    
    public int solution(int[][] board) {
        N = board.length;
        dp = new int[N][N][4];
        return bfs(board);
    }
    
    private int bfs(int[][] board) {
        int result = Integer.MAX_VALUE;
        Queue<Car> q = new LinkedList<>();
        q.add(new Car(0, 0, -1, 0));
        
        while (!q.isEmpty()) {
            Car now = q.poll();
            
            if (now.y == N - 1 && now.x == N - 1) result = Math.min(result, now.cost);
            
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d], nx = now.x + dx[d];
                
                if (!isInRange(ny, nx) || board[ny][nx] == 1) continue;
                
                int nextCost = now.cost;
                if (now.dir == -1 || now.dir == d) nextCost += 100;
                else nextCost += 600;
                
                if (dp[ny][nx][d] == 0 || dp[ny][nx][d] > nextCost) {
                    dp[ny][nx][d] = nextCost;
                    q.add(new Car(ny, nx, d, nextCost));
                } 
            }
        }
        
        return result;
    }
    
    private boolean isInRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
}