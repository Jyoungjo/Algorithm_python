import java.util.*;

class Solution {
    final int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    int N;
    int[][] BOARD;
    
    public int solution(int[][] board) {
        N = board.length;
        BOARD = board;
        return play();
    }
    
    private int play() {
        int cnt = 0;
        
        while (true) {
            boolean removed = false;
            boolean[][] visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int id = BOARD[i][j];
                    
                    if (id == 0 || visited[i][j]) continue;
                    
                    List<int[]> points = bfs(i, j, id, visited);
                    if (isCanRemove(points)) {
                        remove(points);
                        removed = true;
                        cnt++;
                    }
                }
            }
            
            if (!removed) break;
        }
        
        return cnt;
    }
    
    private void remove(List<int[]> points) {
        for (int[] p : points) BOARD[p[0]][p[1]] = 0;
    }
    
    private boolean isCanRemove(List<int[]> points) {
        int[] vals = calMaxMin(points);
        int r1 = vals[0], r2 = vals[1], c1 = vals[2], c2 = vals[3];
        
        int id = BOARD[points.get(0)[0]][points.get(0)[1]];
        List<int[]> empty = findEmpty(r1, r2, c1, c2, id);
        if (empty.isEmpty()) return false;
        
        return isAllBlank(empty);
    }
    
    private boolean isAllBlank(List<int[]> empty) {
        for (int[] p : empty) {
            for (int i = 0; i <= p[0]; i++) {
                if (BOARD[i][p[1]] != 0) return false;
            }
        }
        
        return true;
    }
    
    private List<int[]> findEmpty(int r1, int r2, int c1, int c2, int id) {
        List<int[]> result = new ArrayList<>();
        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                if (BOARD[r][c] == 0) result.add(new int[]{r, c});
            }
        }
        
        if (result.size() != 2) return Collections.emptyList();
        
        return result;
    }
    
    private int[] calMaxMin(List<int[]> points) {
        int r1 = Integer.MAX_VALUE, r2 = Integer.MIN_VALUE, c1 = Integer.MAX_VALUE, c2 = Integer.MIN_VALUE;
        
        for (int[] p : points) {
            r1 = Math.min(r1, p[0]);
            r2 = Math.max(r2, p[0]);
            c1 = Math.min(c1, p[1]);
            c2 = Math.max(c2, p[1]);
        }
        
        return new int[]{r1, r2, c1, c2};
    }
    
    private List<int[]> bfs(int i, int j, int id, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        visited[i][j] = true;
        
        List<int[]> points = new ArrayList<>();
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int y = now[0], x = now[1];
            
            points.add(new int[]{y, x});
            
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d], nx = x + dx[d];
                
                if (!isInRange(ny, nx) || visited[ny][nx] || BOARD[ny][nx] != id) continue;
                
                q.add(new int[]{ny, nx});
                visited[ny][nx] = true;
            }
        }
        
        return points;
    }
    
    private boolean isInRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
}