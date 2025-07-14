import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            matrix[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        int[][] dp = new int[N][N];
        for (int len = 1; len < N; len++) {
            for (int i = 0; i < N - len; i++) {
                dp[i][i + len] = 987654321;
                for (int mid = i; mid < i + len; mid++) {
                    int val = dp[i][mid] + dp[mid + 1][i + len]
                            + (matrix[i][0] * matrix[mid][1] * matrix[i + len][1]);
                    dp[i][i + len] = Math.min(dp[i][i + len], val);
                }
            }
        }
        System.out.println(dp[0][N - 1]);
    }
}