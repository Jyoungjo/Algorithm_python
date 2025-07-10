import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] home = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) home[i][j] = Integer.parseInt(st.nextToken());
        }

        int[][][] dp = new int[N][N][3];
        dp[0][0][0] = 1; dp[0][1][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 2; j < N; j++) {
                if (home[i][j] == 0) {
                    if (i == 0) {
                        dp[i][j][0] += dp[i][j - 1][0];
                        continue;
                    }
                    // 가로 -> 가로
                    dp[i][j][0] += dp[i][j - 1][0];
                    // 대각 -> 가로
                    dp[i][j][0] += dp[i][j - 1][2];
                    // 대각 -> 세로
                    dp[i][j][1] += dp[i - 1][j][2];
                    // 세로 -> 세로
                    dp[i][j][1] += dp[i - 1][j][1];

                    if (home[i - 1][j] == 0 && home[i][j - 1] == 0) {
                        // 가로 -> 대각
                        dp[i][j][2] += dp[i - 1][j - 1][0];
                        // 대각 -> 대각
                        dp[i][j][2] += dp[i - 1][j - 1][2];
                        // 세로 -> 대각
                        dp[i][j][2] += dp[i - 1][j - 1][1];
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < 3; i++) answer += dp[N - 1][N - 1][i];

        System.out.println(answer);
    }
}