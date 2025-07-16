import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[][] dp;
    static final int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] forest = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) forest[i][j] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n][n];
        int answer = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) answer = Math.max(answer, assign(forest, i, j));
        }

        System.out.println(answer);
    }

    private static int assign(int[][] forest, int y, int x) {
        if (dp[y][x] != 0) return dp[y][x];

        int result = 0;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d], nx = x + dx[d];
            if (!isWithinRange(ny, nx)) continue;
            if (forest[y][x] < forest[ny][nx]) result = Math.max(result, assign(forest, ny, nx));
        }

        return dp[y][x] = result + 1;
    }

    private static boolean isWithinRange(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}