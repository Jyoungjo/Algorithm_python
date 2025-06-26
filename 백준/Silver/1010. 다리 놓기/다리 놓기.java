import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int a = 0; a < size; a++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
            int[][] dp = new int[M + 1][N + 1]; // return 다리 지을 경우의 수
            sb.append(combination(M, N, dp)).append("\n");
        }
        System.out.print(sb);
    }

    private static int combination(int n, int r, int[][] dp) {
        if (dp[n][r] > 0) return dp[n][r];
        if (r == 0 || r == n) return dp[n][r] = 1;
        return dp[n][r] = combination(n - 1, r - 1, dp) + combination(n - 1, r, dp);
    }
}