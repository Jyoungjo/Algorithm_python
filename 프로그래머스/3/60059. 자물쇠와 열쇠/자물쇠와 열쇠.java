import java.util.*;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int len = lock.length + (key.length - 1) * 2;
        int[][] exLock = new int[len][len];
        
        for (int i = key.length - 1; i < key.length - 1 + lock.length; i++) {
            for (int j = key.length - 1; j < key.length - 1 + lock.length; j++) {
                exLock[i][j] = lock[i - (key.length - 1)][j - (key.length - 1)];
            }
        }
        
        for (int r = 0; r < 4; r++) {
            if (isMatch(key, exLock, lock.length)) return true;
            rotate(key);
        }
        
        return false;
    }
    
    private boolean isMatch(int[][] key, int[][] exLock, int lockLen) {
        for (int dy = 0; dy < key.length - 1 + lockLen; dy++) {
            for (int dx = 0; dx < key.length - 1 + lockLen; dx++) {
                for (int y = 0; y < key.length; y++) {
                    for (int x = 0; x < key.length; x++) {
                        exLock[y + dy][x + dx] += key[y][x];
                    }
                }
                
                if (isSolved(exLock, key.length - 1, key.length - 1 + lockLen)) return true;
                
                for (int y = 0; y < key.length; y++) {
                    for (int x = 0; x < key.length; x++) {
                        exLock[y + dy][x + dx] -= key[y][x];
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean isSolved(int[][] exLock, int s, int e) {
        for (int i = s; i < e; i++) {
            for (int j = s; j < e; j++) {
                if (exLock[i][j] != 1) return false;
            }
        }
        
        return true;
    }
    
    private void rotate(int[][] key) {
        int[][] tmp = new int[key.length][key.length];
        
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                tmp[i][j] = key[j][key.length - 1 - i];
            }
        }
        
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                key[i][j] = tmp[i][j];
            }
        }
    }
}