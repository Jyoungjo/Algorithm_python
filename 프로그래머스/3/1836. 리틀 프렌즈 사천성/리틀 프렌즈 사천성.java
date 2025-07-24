import java.util.*;

class Solution {
    char[][] n_board;
    int M, N;
    Map<Character, List<int[]>> map = new HashMap<>();
    StringBuilder sb = new StringBuilder();
    
    public String solution(int m, int n, String[] board) {
        M = m; N = n;
        n_board = new char[m][n];
        for (int i = 0; i < m; i++) n_board[i] = board[i].toCharArray();
        putToMap();
        
        List<Character> keys = new LinkedList<>(map.keySet());
        Collections.sort(keys);
        int idx = 0;
        
        while (!keys.isEmpty()) {
            char now = keys.get(idx);
            if (canRemove(now)) {
                removeBlock(now);
                keys.remove(idx);
                idx = 0;
            } else {
                idx++;
                if (idx == keys.size()) return "IMPOSSIBLE";
            }
        }
        
        return sb.toString();
    }
    
    private void putToMap() {        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if ('A' <= n_board[i][j] && n_board[i][j] <= 'Z') {
                    map.putIfAbsent(n_board[i][j], new ArrayList<>());
                    map.get(n_board[i][j]).add(new int[]{i, j});
                }
            }
        }
    }
    
    private boolean canRemove(char now) {
        List<int[]> coord = map.get(now);
        int[] first = coord.get(0), second = coord.get(1);
        int y1 = first[0], x1 = first[1], y2 = second[0], x2 = second[1];
        
        if (x1 < x2) {
            if (hasOneValInRow(now, x1, x2, y1) && hasOneValInCol(now, y1, y2, x2)) return true;
            if (hasOneValInRow(now, x1, x2, y2) && hasOneValInCol(now, y1, y2, x1)) return true;
        } else {
            if (hasOneValInRow(now, x2, x1, y2) && hasOneValInCol(now, y1, y2, x1)) return true;
            if (hasOneValInRow(now, x2, x1, y1) && hasOneValInCol(now, y1, y2, x2)) return true;
        }
        
        return false;
    }
    
    private boolean hasOneValInRow(char now, int c1, int c2, int r) {
        for (int c = c1; c <= c2; c++) if (n_board[r][c] != '.' && n_board[r][c] != now) return false;
        return true;
    }
    
    private boolean hasOneValInCol(char now, int r1, int r2, int c) {
        for (int r = r1; r <= r2; r++) if (n_board[r][c] != '.' && n_board[r][c] != now) return false;
        return true;
    }
    
    private void removeBlock(char now) {
        for (int[] coord : map.get(now)) n_board[coord[0]][coord[1]] = '.';
        sb.append(now);
    }
}