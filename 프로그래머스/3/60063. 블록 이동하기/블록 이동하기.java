import java.util.*;

class Solution {
    final int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    int R, C;
    
    public int solution(int[][] board) {
        R = board.length; C = board[0].length;
        return move(board);
    }
    
    private int move(int[][] board) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[R][C][2];
        q.add(new int[]{0, 0, 0, 1, 0, 0});
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int ly = now[0], lx = now[1], ry = now[2], rx = now[3], cnt = now[4], dir = now[5];
            
            // 이동하려는 곳이 영역 밖일 경우 continue
            if (!isWithinRange(ly, lx) || !isWithinRange(ry, rx)) continue;
            
            // 이동하려는 곳이 벽일 경우 continue
            if (board[ly][lx] == 1 || board[ry][rx] == 1) continue;
            
            // 이동하려는 곳이 이미 방문한 경우 continue
            if (visited[ly][lx][dir] && visited[ry][rx][dir]) continue;
            
            if ((ly == R - 1 && lx == C - 1) || (ry == R - 1 && rx == C - 1)) return cnt;
            
            visited[ly][lx][dir] = true;
            visited[ry][rx][dir] = true;
            
            // 이동
            for (int d = 0; d < 4; d++) {
                int nly = ly + dy[d], nlx = lx + dx[d], nry = ry + dy[d], nrx = rx + dx[d];
                q.add(new int[]{nly, nlx, nry, nrx, cnt + 1, dir});
            }
            
            // 회전
            if (dir == 0) { // 가로
                // 회전 방향 = 위쪽
                if (ry - 1 >= 0 && isAllBlank(board, ly - 1, lx, ry - 1, rx)) {
                    q.add(new int[]{ry - 1, lx, ly, lx, cnt + 1, 1});
                    q.add(new int[]{ly - 1, rx, ry, rx, cnt + 1, 1});
                }
                // 회전 방향 = 아래쪽
                if (ry + 1 < R && isAllBlank(board, ly + 1, lx, ry + 1, rx)) {
                    q.add(new int[]{ly, lx, ry + 1, lx, cnt + 1, 1});
                    q.add(new int[]{ry, rx, ly + 1, rx, cnt + 1, 1});
                }
            } else { // 세로
                // 회전 방향 = 왼쪽
                if (rx - 1 >= 0 && isAllBlank(board, ly, lx - 1, ry, rx - 1)) {
                    q.add(new int[]{ly, rx - 1, ly, lx, cnt + 1, 0});
                    q.add(new int[]{ry, lx - 1, ry, rx, cnt + 1, 0});
                }
                // 회전 방향 = 오른쪽
                if (rx + 1 < C && isAllBlank(board, ly, lx + 1, ry, rx + 1)) {
                    q.add(new int[]{ly, lx, ly, rx + 1, cnt + 1, 0});
                    q.add(new int[]{ry, rx, ry, lx + 1, cnt + 1, 0});
                }
            }
        }
        
        return 0;
    }
    
    private boolean isWithinRange(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }
    
    private boolean isAllBlank(int[][] board, int y1, int x1, int y2, int x2) {
        return board[y1][x1] == 0 && board[y2][x2] == 0;
    }
}