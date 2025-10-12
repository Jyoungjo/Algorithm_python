public class Solution {
    int N, PP, QQ;
    int[][] LAND;
    long l, r;
    
    public long solution(int[][] land, int P, int Q) {
        init(land, P, Q);
        findRange();
        return cal();
    }
    
    private long cal() {
        long cost = 0;
        
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            
            long lVal = calCost(mid), rVal = calCost(mid + 1);
            
            if (lVal > rVal) {
                cost = rVal;
                l = mid + 1;
            } else {
                cost = lVal;
                r = mid - 1;
            }
        }
        
        return cost;
    }
    
    private long calCost(long height) {
        long tmp = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (LAND[i][j] < height) tmp += PP * (height - LAND[i][j]);
                else tmp += QQ * (LAND[i][j] - height);
            }
        }
        
        return tmp;
    }
    
    private void findRange() {
        l = LAND[0][0]; r = LAND[0][0];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (l > LAND[i][j]) l = LAND[i][j];
                if (r < LAND[i][j]) r = LAND[i][j];
            }
        }
    }
    
    private void init(int[][] land, int P, int Q) {
        N = land.length;
        LAND = land;
        PP = P;
        QQ = Q;
    }
}