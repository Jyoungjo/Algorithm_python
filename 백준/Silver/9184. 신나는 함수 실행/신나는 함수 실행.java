import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int[][][] dp;
    static final int MAX = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp = new int[102][102][102];
        for (int i = 0; i < 102; i++) {
            for (int j = 0; j < 102; j++) Arrays.fill(dp[i][j], MAX);
        }
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1 && c == -1) break;
            System.out.printf("w(%d, %d, %d) = %d\n", a, b, c, w(a + 50, b + 50, c + 50));
        }
    }

    private static int w(int a, int b, int c) {
        if (dp[a][b][c] != MAX) return dp[a][b][c];
        if (a <= 50 || b <= 50 || c <= 50) return dp[a][b][c] = 1;

        if (a > 70 || b > 70 || c > 70) return dp[a][b][c] = w(70, 70, 70);
        if (a < b && b < c) return dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);

        return dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
    }
}