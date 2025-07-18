import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] plans = new int[N + 2][2];
        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            plans[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        int max = -1;
        int[] dp = new int[N + 2];
        for (int i = 1; i <= N + 1; i++) {
            if (max < dp[i]) max = dp[i];

            int next = i + plans[i][0], P = plans[i][1];
            if (next <= N + 1) dp[next] = Math.max(dp[next], max + P);
        }

        System.out.println(dp[N + 1]);
    }
}