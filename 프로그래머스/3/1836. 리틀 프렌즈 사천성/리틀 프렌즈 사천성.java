import java.util.*;
import java.util.stream.*;

class Solution {
    final char BLANK = '.', WALL = '*';
    final String IMPOSSIBLE = "IMPOSSIBLE";
    Map<Character, List<int[]>> alphaMap = new HashMap<>();
    int M, N;
    char[][] BOARD;
    StringBuilder sb = new StringBuilder();
    
    public String solution(int m, int n, String[] board) {
        init(m, n, board);
        buildMap();
        return search(alphaMap.keySet());
    }
    
    private void init(int m, int n, String[] board) {
        M = m; N = n;
        BOARD = new char[M][N];
        for (int i = 0; i < M; i++) BOARD[i] = board[i].toCharArray();
    }
    
    private void buildMap() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                char alpha = BOARD[i][j];
                if ('A' <= alpha && alpha <= 'Z') {
                    alphaMap.putIfAbsent(alpha, new ArrayList<>());
                    alphaMap.get(alpha).add(new int[]{i, j});
                }
            }
        }
    }
    
    private String search(Set<Character> keys) {
        List<Character> remaining = new LinkedList<>(keys);
        Collections.sort(remaining);
        
        int idx = 0;
        while (!remaining.isEmpty()) {
            char now = remaining.get(idx);
            List<int[]> pair = alphaMap.get(now);
            if (canRemove(pair.get(0), pair.get(1), now)) {
                remaining.remove(idx);
                remove(pair, now);
                idx = 0;
            } else {
                idx++;
                if (idx >= remaining.size()) return IMPOSSIBLE;
            }
        }
        
        return sb.toString();
    }
    
    private boolean canRemove(int[] first, int[] second, char target) {
        int y1 = first[0], x1 = first[1], y2 = second[0], x2 = second[1];
        if (x1 < x2) {
            if (isOneByRow(x1, y1, y2, target) && isOneByColumn(y2, x1, x2, target)) return true;
            if (isOneByColumn(y1, x1, x2, target) && isOneByRow(x2, y1, y2, target)) return true;
        } else {
            if (isOneByRow(x2, y1, y2, target) && isOneByColumn(y1, x2, x1, target)) return true;
            if (isOneByColumn(y2, x2, x1, target) && isOneByRow(x1, y1, y2, target)) return true;
        }
        
        return false;
    }
    
    private boolean isOneByRow(int col, int s, int e, char target) {
        for (int row = s; row <= e; row++) {
            if (BOARD[row][col] == BLANK) continue;
            if (BOARD[row][col] == WALL || BOARD[row][col] != target) return false;
        }
        
        return true;
    }
    
    private boolean isOneByColumn(int row, int s, int e, char target) {
        for (int col = s; col <= e; col++) {
            if (BOARD[row][col] == BLANK) continue;
            if (BOARD[row][col] == WALL || BOARD[row][col] != target) return false;
        }
        
        return true;
    }
    
    private void remove(List<int[]> pair, char target) {
        int[] f = pair.get(0), s = pair.get(1);
        BOARD[f[0]][f[1]] = BLANK;
        BOARD[s[0]][s[1]] = BLANK;
        
        sb.append(target);
    }
}