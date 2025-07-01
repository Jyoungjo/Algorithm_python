import java.util.*;

class Solution {
    Map<Integer, List<int[]>> cards = new HashMap<>();
    int R, C, pairCnt;
    boolean[] visited;
    int answer = Integer.MAX_VALUE;
    int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    
    public int solution(int[][] board, int r, int c) {
        init(board);
        play(board, new int[]{r, c}, 0, 0);
        return answer;
    }
    
    private void init(int[][] board) {
        R = 4; C = 4;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] != 0) {
                    cards.putIfAbsent(board[i][j], new ArrayList<>());
                    cards.get(board[i][j]).add(new int[]{i, j});
                }
            }
        }
        pairCnt = cards.keySet().size();
        visited = new boolean[7];
    }
    
    private void play(int[][] board, int[] now, int mv, int depth) {
        if (depth == pairCnt) {
            answer = Math.min(mv, answer);
            return;
        }
        
        for (int cardNum : cards.keySet()) {
            if (visited[cardNum]) continue;
            
            visited[cardNum] = true;
            int r = now[0], c = now[1];
            List<int[]> cardList = cards.get(cardNum);
            
            int moveCnt1 = move(board, r, c, cardList.get(0), cardList.get(1));
            play(board, cardList.get(1), mv + moveCnt1, depth + 1);
            board[cardList.get(0)[0]][cardList.get(0)[1]] = cardNum;
            board[cardList.get(1)[0]][cardList.get(1)[1]] = cardNum;
            
            
            int moveCnt2 = move(board, r, c, cardList.get(1), cardList.get(0));
            play(board, cardList.get(0), mv + moveCnt2, depth + 1);
            board[cardList.get(1)[0]][cardList.get(1)[1]] = cardNum;
            board[cardList.get(0)[0]][cardList.get(0)[1]] = cardNum;
            
            visited[cardNum] = false;
        }
    }
    
    private int move(int[][] board, int r, int c, int[] first, int[] second) {
        int result = 0;
        
        result += bfs(board, new int[]{r, c}, first);
        result += bfs(board, first, second);
        
        board[first[0]][first[1]] = 0;
        board[second[0]][second[1]] = 0;
        
        return result + 2;
    }
    
    private int bfs(int[][] board, int[] s, int[] e) {
        int[][] result = new int[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(result[i], Integer.MAX_VALUE);
        }
        result[s[0]][s[1]] = 0;
        
        Deque<int[]> q = new ArrayDeque<>();
        q.add(s);
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0], c = now[1];
            
            for (int d = 0; d < 4; d++) {
                // 한칸 이동
                int nr = r + dr[d], nc = c + dc[d];
                if (!isWithinRange(nr, nc)) continue;
                
                if (result[nr][nc] > result[r][c] + 1) {
                    result[nr][nc] = result[r][c] + 1;
                    q.add(new int[]{nr, nc});
                }
                
                // Ctrl 누르고 이동
                
                // 한칸 이동한 결과가 다른 카드라면 다음 방향
                if (board[nr][nc] != 0) continue;
                
                while (board[nr][nc] == 0) {
                    if (!isWithinRange(nr + dr[d], nc + dc[d])) break;
                    nr += dr[d];
                    nc += dc[d];
                }
                
                if (result[nr][nc] > result[r][c] + 1) {
                    result[nr][nc] = result[r][c] + 1;
                    q.add(new int[]{nr, nc});
                }
            }
        }
        
        return result[e[0]][e[1]];
    }
    
    private boolean isWithinRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}