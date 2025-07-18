import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        final int INF = 987654321;
        int[][] costs = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            costs[i] = new int[]{r, g, b};
        }

        int answer = INF;
        int[][] dp = new int[N][3];
        for (int color = 0; color < 3; color++) {
            for (int i = 0; i < 3; i++) {
                if (i == color) dp[0][i] = costs[0][i];
                else dp[0][i] = INF;
            }

            for (int i = 1; i < N; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
            }

            // 마지막 집의 경우 첫 집과 색이 같으면 안 되기 때문에 같은 색상의 결과값 제외하고 찾기
            for (int i = 0; i < 3; i++) if (i != color) answer = Math.min(answer, dp[N - 1][i]);
        }

        System.out.println(answer);
    }
}