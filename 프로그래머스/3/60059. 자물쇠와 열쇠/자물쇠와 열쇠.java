import java.util.*;

class Solution {
    List<int[]> keys = new ArrayList<>();
    Set<String> locks = new HashSet<>();
    int M, N;
    
    public boolean solution(int[][] key, int[][] lock) {
        M = key.length; N = lock.length;
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (key[i][j] == 1) keys.add(new int[]{i, j});
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (lock[i][j] == 0) locks.add(i + "," + j);
            }
        }
        
        for (int d = 0; d < 4; d++) {
            rotate();
            for (int dy = -M + 1; dy < N; dy++) {
                for (int dx = -M + 1; dx < N; dx++) {
                    if (isMatch(lock, dy, dx)) return true;
                }
            }
        }
        
        return false;
    }
    
    private void rotate() {
        for (int[] k : keys) {
            int y = k[0], x = k[1];
            int tmp = y;
            y = x;
            x = M - tmp - 1;
            k[0] = y;
            k[1] = x;
        }
    }
    
    private boolean isMatch(int[][] lock, int dy, int dx) {
        Set<String> keysSet = new HashSet<>();
        for (int[] k : keys) {
            int yy = k[0] + dy, xx = k[1] + dx;
            keysSet.add(yy + "," + xx);
        }
                
        for (String l : locks) {
            if (!keysSet.contains(l)) return false;
        }
                
        for (String k : keysSet) {
            String[] kk = k.split(",");
            int y = Integer.parseInt(kk[0]), x = Integer.parseInt(kk[1]);
            if (0 <= y && y < N && 0 <= x && x < N) {
                if (lock[y][x] == 1) return false;
            }
        }
        
        return true;
    }
}