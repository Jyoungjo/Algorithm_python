import java.util.*;

class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long r1 = x, c1 = y, r2 = x, c2 = y;
        
        for (int i = queries.length - 1; i >= 0; i--) {
            int dir = queries[i][0], cnt = queries[i][1];
            // 모든 경우를 역으로 추적
            
            // 우 -> 좌 로 진행하기 때문에 역으로 좌 -> 우로 계산
            if (dir == 0) {
                if (c1 != 0) c1 += cnt;
                c2 = Math.min(c2 + cnt, m - 1);
                if (c1 > m - 1) return 0;
            }
            // 좌 -> 우 로 진행하기 때문에 역으로 우 -> 좌로 계산
            else if (dir == 1) {
                if (c2 != m - 1) c2 -= cnt;
                c1 = Math.max(c1 - cnt, 0);
                if (c2 < 0) return 0;
            }
            // 하 -> 상으로 진행하기 때문에 역으로 상 -> 하로 계산
            else if (dir == 2) {
                if (r1 != 0) r1 += cnt;
                r2 = Math.min(r2 + cnt, n - 1);
                if (r1 > n - 1) return 0;
            }
            // 상 -> 하로 진행하기 때문에 역으로 하 -> 상으로 계산
            else {
                if (r2 != n - 1) r2 -= cnt;
                r1 = Math.max(r1 - cnt, 0);
                if (r2 < 0) return 0;
            }
        }
        
        return (r2 - r1 + 1) * (c2 - c1 + 1);
    }
}