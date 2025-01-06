import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] area;

    public static int solution() {
        int[][] dp = new int[N][M];
        dp[0][0] = area[0][0];
        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i - 1] + area[0][i];
        }

        for (int i = 1; i < N; i++) {
            int[] l = new int[M], r = new int[M];

            l[0] = dp[i - 1][0] + area[i][0];
            for (int j = 1; j < M; j++) {
                l[j] = Math.max(l[j - 1] + area[i][j], dp[i - 1][j] + area[i][j]);
            }

            r[M - 1] = dp[i - 1][M - 1] + area[i][M - 1];
            for (int j = M - 2; j >= 0; j--) {
                r[j] = Math.max(r[j + 1] + area[i][j], dp[i - 1][j] + area[i][j]);
            }

            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(l[j], r[j]);
            }
        }

        return dp[N - 1][M - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        area = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());
    }
}