import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[N + 1];
        dp[1] = 1;
        for (int i = 2; i <= N; i++) dp[i] = dp[i - 1] + dp[i - 2];
        System.out.println(dp[N] * 4 + dp[N - 1] * 2);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}