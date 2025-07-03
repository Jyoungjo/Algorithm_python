import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        final int MOD = 10007;
        long[][] dp = new long[N + 1][10];
        Arrays.fill(dp[1], 1);

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = j; k < 10; k++) dp[i][j] += (dp[i - 1][k]) % MOD;
            }
        }

        long cnt = 0;
        for (int i = 0; i < 10; i++) cnt += dp[N][i];
        System.out.println(cnt % MOD);
    }
}