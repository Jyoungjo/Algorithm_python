import java.util.*;

class Solution {
    final String IMPOSSIBLE = "IMPOSSIBLE";
    final char BLANK = '.';
    Map<Character, List<int[]>> tiles;
    char[][] BOARD;
    int M, N;
    
    public String solution(int m, int n, String[] board) {
        init(m, n, board);
        return play();
    }
    
    private void init(int m, int n, String[] board) {
        tiles = new HashMap<>();
        M = m; N = n;
        BOARD = new char[m][n];
        
        for (int i = 0; i < m; i++) {
            BOARD[i] = board[i].toCharArray();
        }
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                char now = board[i].charAt(j);
                if ('A' <= now && now <= 'Z') {
                    tiles.putIfAbsent(now, new ArrayList<>());
                    tiles.get(now).add(new int[]{i, j});
                }
            }
        }
    }
    
    private String play() {
        String result = "";
        List<Character> alpha = new LinkedList<>(tiles.keySet());
        Collections.sort(alpha);
        
        int idx = 0;
        while (!alpha.isEmpty()) {
            char now = alpha.get(idx);
            if (canRemove(now)) {
                alpha.remove(idx);
                remove(now);
                idx = 0;
                result += now;
            } else {
                idx++;
                if (idx == alpha.size()) return IMPOSSIBLE;
            }
        }
        return result;
    }
    
    private boolean canRemove(char alpha) {
        List<int[]> coords = tiles.get(alpha);
        int y1 = coords.get(0)[0], x1 = coords.get(0)[1], y2 = coords.get(1)[0], x2 = coords.get(1)[1];
        
        if (x1 < x2) {
            if (isEmptyRow(alpha, y1, y2, x1) && isEmptyColumn(alpha, x1, x2, y2)) return true;
            if (isEmptyRow(alpha, y1, y2, x2) && isEmptyColumn(alpha, x1, x2, y1)) return true;
        } else {
            if (isEmptyRow(alpha, y1, y2, x2) && isEmptyColumn(alpha, x2, x1, y1)) return true;
            if (isEmptyRow(alpha, y1, y2, x1) && isEmptyColumn(alpha, x2, x1, y2)) return true;
        }
        
        return false;
    }
    
    private boolean isEmptyRow(char target, int r1, int r2, int c) {
        for (int r = r1; r <= r2; r++) {
            if (BOARD[r][c] != BLANK && BOARD[r][c] != target) return false;
        }
        
        return true;
    }
    
    private boolean isEmptyColumn(char target, int c1, int c2, int r) {
        for (int c = c1; c <= c2; c++) {
            if (BOARD[r][c] != BLANK && BOARD[r][c] != target) return false;
        }
        
        return true;
    }
    
    private void remove(char target) {
        List<int[]> coords = tiles.get(target);
        BOARD[coords.get(0)[0]][coords.get(0)[1]] = BLANK;
        BOARD[coords.get(1)[0]][coords.get(1)[1]] = BLANK;
    }
}