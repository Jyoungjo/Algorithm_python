import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        final int MOD = 1000000009;
        long[][] dp = new long[100001][4];
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1; dp[3][2] = 1; dp[3][3] = 1;
        for (int i = 4; i <= 100000; i++) {
            for (int j = 1; j <= 3; j++) {
                for (int k = 1; k <= 3; k++) if (j != k) dp[i][j] += (dp[i - j][k]) % MOD;
            }
        }
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            if (n == 1 || n == 2) {
                System.out.println(1);
                continue;
            }
            System.out.println((dp[n][1] + dp[n][2] + dp[n][3]) % MOD);
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}