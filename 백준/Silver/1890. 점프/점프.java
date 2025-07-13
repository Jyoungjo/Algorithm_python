import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) board[i][j] = Integer.parseInt(st.nextToken());
        }
        long[][] dp = new long[N][N];
        dp[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 1; k < N; k++) {
                    int y = i - k, x = j - k;
                    if (y >= 0 && board[y][j] == k) dp[i][j] += dp[y][j];
                    if (x >= 0 && board[i][x] == k) dp[i][j] += dp[i][x];
                }
            }
        }

        System.out.println(dp[N - 1][N - 1]);
    }
}