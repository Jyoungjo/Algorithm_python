import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) weights[i] = Integer.parseInt(st.nextToken());

        boolean[][] dp = new boolean[n + 1][40001];
        int w = 0;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = true;
            w += weights[i - 1];
            for (int j = 1; j <= 40000; j++) {
                if (w < j) break;

                int abs = Math.abs(j - weights[i - 1]);
                if (abs >= 0 && dp[i - 1][abs]) dp[i][j] = true;
                else if (j + weights[i - 1] <= 40000 && dp[i - 1][j + weights[i - 1]]) dp[i][j] = true;
                else if (j == weights[i - 1]) dp[i][j] = true;
                else dp[i][j] = dp[i - 1][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int x = Integer.parseInt(st.nextToken());
            sb.append(dp[n][x] ? "Y" : "N").append(" ");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}