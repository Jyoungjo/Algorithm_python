import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        final long MOD = 1000000000;
        long[][] dp = new long[N + 1][10]; // dp[자릿수][0~9] = 계단수의 갯수
        for (int i = 1; i < 10; i++) dp[1][i] = 1;

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) dp[i][j] = dp[i - 1][1];
                else if (j == 9) dp[i][j] = dp[i - 1][8];
                else dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
            }
        }

        long answer = 0;
        for (int i = 0; i < 10; i++) answer += dp[N][i];

        System.out.println(answer % MOD);
    }
}