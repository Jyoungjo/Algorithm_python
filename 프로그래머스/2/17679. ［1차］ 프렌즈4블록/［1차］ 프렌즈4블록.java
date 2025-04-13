import java.util.*;
import java.util.stream.*;

class Solution {
    int R, C;
    int answer = 0;
    char[][] BOARD;
    final char BLANK = '-';
    
    public int solution(int m, int n, String[] board) {
        /*
            1. 지울 블록이 있는가 탐색
            2. 있다면 블록 삭제 (바로 지우는 것이 아닌 좌표를 저장해두고 한번에 지우기)
            3. 다 지웠다면 남은 블록들 아래로 떨구기
            4. 1부터 반복
            5. 끝났다면 지운 블록 수 리턴
        */
        R = m; C = n;
        BOARD = new char[R][C];
        for (int i = 0; i < board.length; i++) {
            BOARD[i] = board[i].toCharArray();
        }
        
        while (true) {
            // 블록 탐색하기
            List<int[]> blocks = findBlocks();
            if (blocks.isEmpty()) break;
            // 찾은 블록 지우기
            removeBlocks(blocks);
            // 블록 아래로 떨구기
            moveBlocks();
        }
        
        return answer;
    }
    
    private void moveBlocks() {
        for (int j = 0; j < C; j++) {
            for (int i = R - 1; i >= 0; i--) {
                int r = i;
                while (r < R && BOARD[r][j] != BLANK && r + 1 < R && BOARD[r + 1][j] == BLANK) {
                    BOARD[r + 1][j] = BOARD[r][j];
                    BOARD[r][j] = BLANK;
                    r++;
                }
            }
        }
    }
    
    private void removeBlocks(List<int[]> blocks) {
        for (int[] block : blocks) {
            BOARD[block[0]][block[1]] = BLANK;
            answer++;
        }
    }
    
    private List<int[]> findBlocks() {
        List<int[]> result = new ArrayList<>();
        boolean[][] visited = new boolean[R][C];
        for (int i = 0; i < R - 1; i++) {
            for (int j = 0; j < C - 1; j++) {
                if (BOARD[i][j] != BLANK &&
                        BOARD[i][j] == BOARD[i + 1][j] &&
                        BOARD[i][j] == BOARD[i][j + 1] &&
                        BOARD[i][j] == BOARD[i + 1][j + 1])
                {
                    visited[i][j] = true;
                    visited[i + 1][j] = true;
                    visited[i][j + 1] = true;
                    visited[i + 1][j + 1] = true;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (visited[i][j]) result.add(new int[]{i, j});
            }
        }
        return result;
    }
}