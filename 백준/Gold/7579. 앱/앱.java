import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // A의 메모리 = m, 활성화 비용 c
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        int[] costs = new int[N], memories = new int[N];
        final int INF = 987654321;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) memories[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) costs[i] = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N][10001];
        int answer = INF;
        for (int i = 0; i < N; i++) {
            int cost = costs[i], memory = memories[i];
            for (int j = 0; j <= 10000; j++) {
                if (i == 0) {
                    if (j >= cost) dp[i][j] = memory;
                } else {
                    if (j >= cost) dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost] + memory);
                    else dp[i][j] = dp[i - 1][j];
                }

                if (dp[i][j] >= M) answer = Math.min(answer, j);
            }
        }

        System.out.println(answer);
    }
}