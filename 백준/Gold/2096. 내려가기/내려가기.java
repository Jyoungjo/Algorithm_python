import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[3][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int n3 = Integer.parseInt(st.nextToken());

            if (i == 0) {
                dp[0][0] = dp[0][1] = n1;
                dp[1][0] = dp[1][1] = n2;
                dp[2][0] = dp[2][1] = n3;
            } else {
                int bMax1 = dp[0][0], bMax2 = dp[2][0];
                dp[0][0] = Math.max(dp[0][0], dp[1][0]) + n1;
                dp[2][0] = Math.max(dp[1][0], dp[2][0]) + n3;
                dp[1][0] = Math.max(Math.max(bMax1, bMax2), dp[1][0]) + n2;

                int bMin1 = dp[0][1], bMin2 = dp[2][1];
                dp[0][1] = Math.min(dp[0][1], dp[1][1]) + n1;
                dp[2][1] = Math.min(dp[1][1], dp[2][1]) + n3;
                dp[1][1] = Math.min(Math.min(bMin1, bMin2), dp[1][1]) + n2;
            }
        }

        int max = Math.max(dp[0][0], Math.max(dp[1][0], dp[2][0]));
        int min = Math.min(dp[0][1], Math.min(dp[1][1], dp[2][1]));

        System.out.printf("%d %d", max, min);
    }
}