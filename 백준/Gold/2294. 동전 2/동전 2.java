import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) coins[i] = Integer.parseInt(br.readLine());
        int[] dp = new int[k + 1];

        Arrays.sort(coins);

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j - coins[i] >= 0) {
                    if (j - coins[i] == 0) dp[j] = 1;
                    else if (dp[j - coins[i]] != 0) {
                        if (dp[j] == 0) dp[j] = dp[j - coins[i]] + 1;
                        else dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                    }
                }
            }
        }

        System.out.println(dp[k] == 0 ? -1 : dp[k]);
    }
}