import java.util.*;

public class Solution {
    int R, C, P, Q;
    long l, r;
    int[][] LAND;
    
    public long solution(int[][] land, int P, int Q) {
        init(land, P, Q);
        return getMin();
    }
    
    private long getMin() {
        long result = 0;        
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            
            long lVal = calValue(mid), rVal = calValue(mid + 1);
            if (lVal <= rVal) {
                r = mid - 1;
                result = lVal;
            } else {
                l = mid + 1;
                result = rVal;
            }
        }
        
        return result;
    }
    
    private long calValue(long h) {
        long tmp = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (LAND[i][j] > h) tmp += this.Q * (LAND[i][j] - h);
                else if (LAND[i][j] < h) tmp += this.P * (h - LAND[i][j]);
            }
        }
        return tmp;
    }
    
    private void init(int[][] land, int P, int Q) {
        R = land.length; C = land[0].length;
        LAND = land;
        this.P = P;
        this.Q = Q;
        
        l = land[0][0]; r = land[0][0];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                l = Math.min(l, land[i][j]);
                r = Math.max(r, land[i][j]);
            }
        }
    }
}