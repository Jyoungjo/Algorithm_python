import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()), M = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) dp[i] = dp[i - 1] + dp[i - 2];

        int answer = 1, beforeSeat = 0;
        for (int i = 0; i < M; i++) {
            int tmp = Integer.parseInt(br.readLine());
            answer *= dp[tmp - beforeSeat - 1];
            beforeSeat = tmp;
        }

        answer *= dp[N - beforeSeat];
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}