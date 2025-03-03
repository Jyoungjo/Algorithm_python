import java.util.*;

class Solution {
    int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    int R, C;
    boolean[][][] visited;
    List<Integer> answer = new ArrayList<>();
    
    public int[] solution(String[] grid) {
        R = grid.length;
        C = grid[0].length();
        visited = new boolean[R][C][4];
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for (int d = 0; d < 4; d++) {
                    if (visited[i][j][d]) continue;
                    dfs(new int[]{i, j, d}, grid);
                }
            }
        }
        answer.sort(Comparator.naturalOrder());
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private void dfs(int[] start, String[] grid) {
        Deque<int[]> stack = new ArrayDeque<>();
        int sr = start[0], sc = start[1], d = start[2];
        int[] now = move(grid[sr].charAt(sc), sr, sc, d);
        stack.push(new int[]{now[0], now[1], now[2], 1});
        
        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            visited[cur[0]][cur[1]][cur[2]] = true;
            
            if (start[0] == cur[0] && start[1] == cur[1] && start[2] == cur[2]) {
                answer.add(cur[3]);
                return;
            }
            
            int[] next = move(grid[cur[0]].charAt(cur[1]), cur[0], cur[1], cur[2]);
            stack.push(new int[]{next[0], next[1], next[2], cur[3] + 1});
        }
    }
    
    private int[] move(char dir, int r, int c, int d) {
        if (dir == 'L') {
            d += 3;
            d %= 4;
        } else if (dir == 'R') {
            d++;
            d %= 4;
        }
        
        int nr = r + dr[d], nc = c + dc[d];
        nr += R; nr %= R;
        nc += C; nc %= C;
        
        return new int[]{nr, nc, d};
    }
}