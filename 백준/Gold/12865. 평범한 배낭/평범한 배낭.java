import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Thing {
    private final int W, V;

    Thing(int W, int V) {
        this.W = W;
        this.V = V;
    }

    public int getW() { return this.W; }
    public int getV() { return this.V; }
}

class Main {
    int N, K;
    Thing[] things;
    int[][] dp;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        things = new Thing[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            things[i] = new Thing(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                Thing thing = things[i];
                int W = thing.getW(), V = thing.getV();
                if (j < W) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W] + V);
            }
        }

        System.out.println(dp[N][K]);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}