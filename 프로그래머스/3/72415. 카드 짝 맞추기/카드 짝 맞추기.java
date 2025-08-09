import java.util.*;

class Solution {
    final int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    Map<Integer, List<int[]>> cardMap = new HashMap<>();
    Set<Integer> visited = new HashSet<>();
    int[][] BOARD;
    int R, C;
    int answer = 987654321, limit;
    
    public int solution(int[][] board, int r, int c) {
        BOARD = board;
        R = board.length; C = board[0].length;
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] != 0) {
                    cardMap.putIfAbsent(board[i][j], new ArrayList<>());
                    cardMap.get(board[i][j]).add(new int[]{i, j});
                }
            }
        }
        
        limit = cardMap.keySet().size();
        recur(r, c, 0, 0);
        return answer;
    }
    
    private void recur(int r, int c, int depth, int result) {
        if (depth == limit) {
            answer = Math.min(result, answer);
            return;
        }
        
        for (int key : cardMap.keySet()) {
            if (visited.contains(key)) continue;
            
            visited.add(key);
            
            List<int[]> coords = cardMap.get(key);
            int[] first = coords.get(0), second = coords.get(1);
            
            int mv1 = move(r, c, first, second);
            removePair(key);
            recur(second[0], second[1], depth + 1, result + mv1);
            restorePair(key);
            
            int mv2 = move(r, c, second, first);
            removePair(key);
            recur(first[0], first[1], depth + 1, result + mv2);
            restorePair(key);
            
            visited.remove(key);
        }
    }
    
    private int move(int r, int c, int[] dest1, int[] dest2) {
        int cnt1 = bfs(new int[]{r, c}, dest1), cnt2 = bfs(dest1, dest2);
        return cnt1 + cnt2 + 2;
    }
    
    private int bfs(int[] start, int[] end) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(start);
        int[][] minMv = new int[R][C];
        for (int i = 0; i < R; i++) Arrays.fill(minMv[i], 987654321);
        minMv[start[0]][start[1]] = 0;
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int y = now[0], x = now[1];
            
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d], nx = x + dx[d];
                if (!isRange(ny, nx)) continue;
                record(minMv, q, ny, nx, y, x);
                
                if (BOARD[ny][nx] != 0) continue;
                while (BOARD[ny][nx] == 0) {
                    if (!isRange(ny + dy[d], nx + dx[d])) break;
                    ny += dy[d];
                    nx += dx[d];
                }
                
                record(minMv, q, ny, nx, y, x);
            }
        }
        
        return minMv[end[0]][end[1]];
    }
    
    private void record(int[][] arr, Queue<int[]> q, int ny, int nx, int y, int x) {
        if (arr[ny][nx] > arr[y][x] + 1) {
            arr[ny][nx] = arr[y][x] + 1;
            q.add(new int[]{ny, nx});
        }
    }
    
    private boolean isRange(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }
    
    private void removePair(int key) {
        int[] a1 = cardMap.get(key).get(0), a2 = cardMap.get(key).get(1);
        BOARD[a1[0]][a1[1]] = 0;
        BOARD[a2[0]][a2[1]] = 0;
    }
    
    private void restorePair(int key) {
        int[] a1 = cardMap.get(key).get(0), a2 = cardMap.get(key).get(1);
        BOARD[a1[0]][a1[1]] = key;
        BOARD[a2[0]][a2[1]] = key;
    }
}