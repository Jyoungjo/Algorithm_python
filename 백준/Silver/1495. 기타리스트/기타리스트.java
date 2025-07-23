import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), S = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        int[] V = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) V[i] = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][M + 1];
        dp[0][S] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (j - V[i - 1] >= 0) dp[i][j] += dp[i - 1][j - V[i - 1]];
                if (j + V[i - 1] <= M) dp[i][j] += dp[i - 1][j + V[i - 1]];
            }
        }

        int max = -1;
        for (int i = 0; i <= M; i++) if (dp[N][i] != 0) max = Math.max(max, i);

        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}