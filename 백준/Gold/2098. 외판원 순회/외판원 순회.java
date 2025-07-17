import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N;
    static final int INF = 987654321;
    static int[][] W, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) W[i][j] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][(1 << N) - 1];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        System.out.println(tsp(0, 1));
    }

    private static int tsp(int now, int visited) {
        if (visited == ((1 << N) - 1)) {
            if (W[now][0] == 0) return INF;
            return W[now][0];
        }

        if (dp[now][visited] != -1) return dp[now][visited];
        dp[now][visited] = INF;

        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) != 0 || W[now][i] == 0) continue;
            dp[now][visited] = Math.min(W[now][i] + tsp(i, (visited | (1 << i))), dp[now][visited]);
        }

        return dp[now][visited];
    }
}