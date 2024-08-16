import java.util.*;

class Solution {
    int[] dy = new int[]{-1, 0, 1, 0}, dx = new int[]{0, 1, 0, -1};
    
    public int[][] convertRectangle(int[][] rectangle) {
        int[][] field = new int[101][101];
        
        for (int[] rect : rectangle) {
            int leftX = rect[1] * 2, leftY = rect[0] * 2, rightX = rect[3] * 2, rightY = rect[2] * 2;
            
            for (int i = leftY; i <= rightY; i++) {
                for (int j = leftX; j <= rightX; j++) {
                    if (i == leftY || i == rightY || j == leftX || j == rightX) {
                        if (field[i][j] != 2) {
                            field[i][j] = 1;
                        }
                    } else {
                        field[i][j] = 2;
                    }
                }
            }
        }
        
        return field;
    }
    
    public int bfs(int[][] field, int startX, int startY, int endX, int endY) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startY, startX, 0});
        boolean[][] visited = new boolean[101][101];
        visited[startY][startX] = true;
        
        while (!q.isEmpty()) {
            int[] coord = q.poll();
            int y = coord[0], x = coord[1], dist = coord[2];
            
            if (y == endY && x == endX) return dist / 2;
            
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                
                if (checkRange(ny, nx) && !visited[ny][nx] && checkEdge(field, ny, nx)) {
                    q.offer(new int[]{ny, nx, dist + 1});
                    visited[ny][nx] = true;
                }
            }
        }
        
        return 0;
    }
    
    public boolean checkRange(int nextY, int nextX) {
        return 1 <= nextY && nextY < 101 && 1 <= nextX && nextX < 101;
    }
    
    public boolean checkEdge(int[][] field, int nextY, int nextX) {
        return field[nextY][nextX] == 1;
    }
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int startX = characterY, startY = characterX, endX = itemY, endY = itemX;
        
        int[][] field = convertRectangle(rectangle);
        
        return bfs(field, startX * 2, startY * 2, endX * 2, endY * 2);
    }
}