import java.util.*;

class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long r1 = x, c1 = y, r2 = x, c2 = y;
        for (int i = queries.length - 1; i >= 0; i--) {
            int command = queries[i][0], dx = queries[i][1];
            
            if (command == 0) {
                if (c1 != 0) c1 += dx;
                c2 = Math.min(m - 1, c2 + dx);
                if (c1 > m - 1) return 0;
            } else if (command == 1) {
                if (c2 != m - 1) c2 -= dx;
                c1 = Math.max(0, c1 - dx);
                if (c2 < 0) return 0;
            } else if (command == 2) {
                if (r1 != 0) r1 += dx;
                r2 = Math.min(n - 1, r2 + dx);
                if (r1 > n - 1) return 0;
            } else {
                if (r2 != n - 1) r2 -= dx;
                r1 = Math.max(0, r1 - dx);
                if (r2 < 0) return 0;
            }
        }
        
        return (r2 - r1 + 1) * (c2 - c1 + 1);
    }
}