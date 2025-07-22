import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        int[] dp = new int[N];
        final int INF = 987654321;
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (j + arr[j] >= i) dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }

        System.out.println(dp[N - 1] == INF ? -1 : dp[N - 1]);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}