import java.util.*;

class Solution {
    static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    static int r, c;
    static boolean[][] visited;
    
    public int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        r = maps.length;
        c = maps[0].length();
        
        visited = new boolean[r][c];
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (maps[i].charAt(j) != 'X' && !visited[i][j]) {
                    answer.add(bfs(maps, new int[]{i, j}));
                }
            }
        }
        
        answer.sort(Comparator.comparingInt(a -> a));
        
        return answer.isEmpty() ? new int[]{-1} : answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private int bfs(String[] maps, int[] start) {
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        visited[start[0]][start[1]] = true;
        
        int res = 0;
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int y = now[0], x = now[1];
            res += maps[y].charAt(x) - '0';
            
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                
                if (!isWithinRange(nx, ny) || maps[ny].charAt(nx) == 'X' || visited[ny][nx]) continue;
                
                visited[ny][nx] = true;
                q.add(new int[]{ny, nx});
            }
        }
        
        return res;
    }
    
    private boolean isWithinRange(int x, int y) {
        return 0 <= y && y < r && 0 <= x && x < c;
    }
}