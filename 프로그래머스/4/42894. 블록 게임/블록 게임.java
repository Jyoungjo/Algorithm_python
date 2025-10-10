import java.util.*;

class Point {
    int y, x;
    
    Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Solution {
    final int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    int[][] BOARD;
    Map<Integer, List<int[]>> blocks = new HashMap<>();
    int N;
    
    public int solution(int[][] board) {
        BOARD = board;
        N = board.length;
        return search();
    }
    
    private int search() {
        int result = 0;
        while (true) {
            boolean isRemoved = false;
            boolean[][] used = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int id = BOARD[i][j];
                    if (id == 0 || used[i][j]) continue;
                    
                    List<Point> points = bfs(i, j, id, used);
                    if (check(points)) {
                        remove(points);
                        result++;
                        isRemoved = true;
                    }
                }
            }
            
            if (!isRemoved) break;
        }
        
        return result;
    }
    
    private List<Point> bfs(int sy, int sx, int id, boolean[][] visited) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sy, sx));
        visited[sy][sx] = true;
        
        List<Point> result = new ArrayList<>();
        while (!q.isEmpty()) {
            Point now = q.poll();
            
            result.add(now);
            
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d], nx = now.x + dx[d];
                
                if (!isInRange(ny, nx) || visited[ny][nx] || BOARD[ny][nx] != id) continue;
                q.add(new Point(ny, nx));
                visited[ny][nx] = true;
            }
        }
        
        return result;
    }
    
    private boolean isInRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
    
    private boolean check(List<Point> points) {
        int r1 = Integer.MAX_VALUE, c1 = Integer.MAX_VALUE, r2 = Integer.MIN_VALUE, c2 = Integer.MIN_VALUE;
        for (Point p : points) {
            r1 = Math.min(r1, p.y);
            c1 = Math.min(c1, p.x);
            r2 = Math.max(r2, p.y);
            c2 = Math.max(c2, p.x);
        }
        
        int id = BOARD[points.get(0).y][points.get(0).x];
        List<Point> empty = new ArrayList<>();
        
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                int val = BOARD[i][j];
                
                if (val == 0) empty.add(new Point(i, j));
                else if (val != id) return false;
            }
        }
        
        if (empty.size() != 2) return false;
        
        for (Point p : empty) {
            for (int i = 0; i < p.y; i++) {
                if (BOARD[i][p.x] != 0) return false;
            }
        }
        
        return true;
    }
    
    private void remove(List<Point> points) {
        for (Point p : points) BOARD[p.y][p.x] = 0;
    }
}