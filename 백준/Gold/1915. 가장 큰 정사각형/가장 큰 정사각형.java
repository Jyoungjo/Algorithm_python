import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) arr[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
        }

        int[][] dp = new int[n][m];

        int answer = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) {
                    if (i == 0 || j == 0) dp[i][j] = 1;
                    else {
                        dp[i][j] = Math.min(
                                dp[i - 1][j - 1],
                                Math.min(dp[i - 1][j], dp[i][j - 1])
                        ) + 1;
                    }
                }
                answer = Math.max(answer, dp[i][j] * dp[i][j]);
            }
        }

        System.out.println(answer);
    }
}