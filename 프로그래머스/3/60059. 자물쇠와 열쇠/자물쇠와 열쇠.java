import java.util.*;

class Solution {
    int M, N;
    
    public boolean solution(int[][] key, int[][] lock) {
        M = key.length; N = lock.length;
        Set<String> lockSet = new HashSet<>();
        List<int[]> keyList = new ArrayList<>();
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (key[i][j] == 1) keyList.add(new int[]{i, j});
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (lock[i][j] == 0) lockSet.add(i + "," + j);
            }
        }
        
        for (int rot = 0; rot < 4; rot++) {
            List<int[]> rotated = rotate(keyList, rot);
            for (int dy = -M + 1; dy < N; dy++) {
                for (int dx = -M + 1; dx < N; dx++) {
                    if (isMatch(rotated, lock, lockSet, dy, dx)) return true;
                }
            }
        }
        
        return false;
    }
    
    private List<int[]> rotate(List<int[]> key, int rot) {
        List<int[]> result = new ArrayList<>();
        for (int[] k : key) {
            int y = k[0], x = k[1];
            for (int i = 0; i < rot; i++) {
                int tmp = y; y = x; x = M - 1 - tmp;
            }
            result.add(new int[]{y, x});
        }
        return result;
    }
    
    private boolean isMatch(List<int[]> key, int[][] lock, Set<String> lockSet, int dy, int dx) {
        Set<String> keySet = new HashSet<>();
        for (int[] k : key) keySet.add((k[0] + dy) + "," + (k[1] + dx));
        
        for (String l : lockSet) {
            if (!keySet.contains(l)) return false;
        }
        
        for (String k : keySet) {
            String[] tmp = k.split(",");
            int y = Integer.parseInt(tmp[0]), x = Integer.parseInt(tmp[1]);
            if (isWithinRange(y, x) && lock[y][x] == 1) return false;
        }
        
        return true;
    }
    
    private boolean isWithinRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
}