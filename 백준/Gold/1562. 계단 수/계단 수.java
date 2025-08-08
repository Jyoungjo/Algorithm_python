import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][][] dp = new long[N + 1][10][1 << 10];
        for (int i = 1; i < 10; i++) dp[1][i][1 << i] = 1;

        final int MOD = 1_000_000_000;
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < (1 << 10); k++) {
                    int cur = k | (1 << j);
                    if (j == 0) dp[i][j][cur] += dp[i - 1][j + 1][k] % MOD;
                    else if (j == 9) dp[i][j][cur] += dp[i - 1][j - 1][k] % MOD;
                    else dp[i][j][cur] += (dp[i - 1][j - 1][k] % MOD + dp[i - 1][j + 1][k] % MOD) % MOD;
                }
            }
        }

        long answer = 0;
        for (int i = 0; i < 10; i++) {
            answer += dp[N][i][(1 << 10) - 1] % MOD;
            answer %= MOD;
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}