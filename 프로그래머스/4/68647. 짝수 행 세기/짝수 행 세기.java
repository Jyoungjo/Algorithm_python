class Solution {
    final int MOD = 10000000 + 19;
    int R, C;
    int[] ones;
    int[][] A;
    long[][] comb;
    
    public int solution(int[][] a) {
        init(a);
        if (isOdd()) return 0;
        return solve();
    }
    
    private int solve() {
        long[][] dp = new long[C][R + 1];
        dp[0][ones[0]] = combinations(R, ones[0]);
        
        for (int col = 1; col < C; col++) {
            for (int row = 0; row <= R; row++) {
                if (dp[col - 1][row] == 0) continue;
                
                int o = ones[col];
                
                for (int oo = Math.max(0, o - R + row); oo <= Math.min(o, row); oo++) {
                    dp[col][row - oo + (o - oo)] += (dp[col - 1][row] * (combinations(row, oo) * (combinations(R - row, o - oo)) % MOD)) % MOD;
                    dp[col][row - oo + (o - oo)] %= MOD;
                }
            }
        }
        
        return (int) dp[C - 1][0];
    }
    
    private long combinations(int a, int b) {
        if (comb[a][b] != 0) return comb[a][b];
        
        if (b == 0 || a == b) return comb[a][b] = 1;
        return comb[a][b] = (combinations(a - 1, b - 1) + combinations(a - 1, b)) % MOD;
    }
    
    private boolean isOdd() {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (A[i][j] == 1) {
                    sum++;
                    ones[j]++;
                }
            }
        }
        
        return sum % 2 == 1;
    }
    
    private void init(int[][] a) {
        R = a.length; C = a[0].length;
        ones = new int[C];
        A = a;
        comb = new long[R + 1][R + 1];
    }
}