import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[][] arr = new int[size][size];
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[size][size];
        dp[0][0] = arr[0][0];

        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + arr[i][j]);
                dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i - 1][j] + arr[i][j + 1]);
            }
        }

        int answer = -1;
        for (int res : dp[size - 1]) answer = Math.max(answer, res);

        System.out.println(answer);
    }
}