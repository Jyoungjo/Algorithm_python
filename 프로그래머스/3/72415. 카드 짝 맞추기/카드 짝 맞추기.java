import java.util.*;

class Solution {
    int[][] cards = new int[7][4];
    boolean[] bArr = new boolean[7];
    boolean[] visited = new boolean[7];
    int cardPairCnt = 0, answer = Integer.MAX_VALUE;
    final int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    final int R = 4, C = 4, MAX = 16;
    
    public int solution(int[][] board, int r, int c) {
        /*
            현재 커서에서 카드로 이동 후 다음 같은 모양의 카드로 이동하는 과정 반복
            첫 번째 카드를 고를 때, 카드 종류만큼 반복문에서 순회하고 이후 카드를 고르는 건 처음에 선택한 카드의 문양과 같은 카드를 고른다.
        */
        init(board);
        backTracking(board, new int[]{r, c}, 0, 0);
        return answer;
    }
    
    private void backTracking(int[][] board, int[] now, int depth, int cnt) {
        if (depth == cardPairCnt) {
            answer = Math.min(answer, cnt);
            return;
        }
        
        for (int i = 1; i <= 6; i++) {
            if (!bArr[i] || visited[i]) continue;
            visited[i] = true;
            
            int[] coord1 = new int[]{cards[i][0], cards[i][1]};
            int[] coord2 = new int[]{cards[i][2], cards[i][3]};
            
            int move1 = countMove(board, coord1, coord2, now);
            backTracking(board, coord2, depth + 1, cnt + move1);
            board[cards[i][0]][cards[i][1]] = i;
            board[cards[i][2]][cards[i][3]] = i;
            
            int move2 = countMove(board, coord2, coord1, now);
            backTracking(board, coord1, depth + 1, cnt + move2);
            board[cards[i][0]][cards[i][1]] = i;
            board[cards[i][2]][cards[i][3]] = i;
            
            visited[i] = false;
        }
    }
    
    private int countMove(int[][] board, int[] start, int[] end, int[] cursor) {
        int sum = 0;
        sum += bfs(board, cursor, start);
        sum += bfs(board, start, end);
        
        board[start[0]][start[1]] = 0;
        board[end[0]][end[1]] = 0;
        
        return sum + 2;
    }
    
    private int bfs(int[][] board, int[] start, int[] end) {
        int[][] matrix = new int[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(matrix[i], MAX);
        }
        matrix[start[0]][start[1]] = 0;
        
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0], c = now[1];
            
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];
                if (!isWithinRange(nr, nc)) continue;
                
                recordMinVal(q, matrix, nr, nc, r, c);
                if (board[nr][nc] != 0) continue;
                
                while (board[nr][nc] == 0) {
                    if (!isWithinRange(nr + dr[d], nc + dc[d])) break;
                    nr += dr[d];
                    nc += dc[d];
                }
                
                recordMinVal(q, matrix, nr, nc, r, c);
            }
        }
        
        return matrix[end[0]][end[1]];
    }
    
    private void recordMinVal(Queue<int[]> q, int[][] matrix, int nr, int nc, int r, int c) {
        if (matrix[nr][nc] > matrix[r][c] + 1) {
            matrix[nr][nc] = matrix[r][c] + 1;
            q.add(new int[]{nr, nc});
        }
    }
    
    private boolean isWithinRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
    
    private void init(int[][] board) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int num = board[i][j];
                if (num == 0) continue;
                if (!bArr[num]) {
                    bArr[num] = true;
                    cards[num][0] = i;
                    cards[num][1] = j;
                } else {
                    cards[num][2] = i;
                    cards[num][3] = j;
                }
            }
        }
        
        for (int i = 1; i <= 6; i++) {
            if (bArr[i]) cardPairCnt++;
        }
    }
}