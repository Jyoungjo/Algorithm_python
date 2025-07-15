import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int K = Integer.parseInt(br.readLine());
            int[] files = new int[K + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            files[1] = Integer.parseInt(st.nextToken());
            for (int j = 2; j <= K; j++) files[j] = Integer.parseInt(st.nextToken()) + files[j - 1];

            int[][] dp = new int[K + 1][K + 1];
            for (int gap = 1; gap < K; gap++) {
                for (int s = 1; s + gap <= K; s++) {
                    int e = s + gap;
                    dp[s][e] = Integer.MAX_VALUE;
                    for (int m = s; m < e; m++) {
                        dp[s][e] = Math.min(
                                dp[s][e],
                                dp[s][m] + dp[m + 1][e] + files[e] - files[s - 1]
                        );
                    }
                }
            }

            System.out.println(dp[1][K]);
        }
    }
}