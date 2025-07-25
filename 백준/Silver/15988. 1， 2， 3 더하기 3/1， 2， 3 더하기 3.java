import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        final int MOD = 1000000009;
        long[] dp = new long[1000001];
        dp[1] = 1; dp[2] = 2; dp[3] = 4;
        for (int i = 4; i <= 1000000; i++) dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD;

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n] % MOD);
        }
    }
}