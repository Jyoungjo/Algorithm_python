import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) numbers[i] = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());

        boolean[][] dp = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++) dp[i][i] = true;

        for (int len = 2; len <= N; len++) {
            for (int start = 1; start <= N - len + 1; start++) {
                int end = start + len - 1;
                if (len == 2) {
                    if (numbers[start] == numbers[end]) dp[start][end] = true;
                    continue;
                }

                if (dp[start + 1][end - 1] && numbers[start] == numbers[end]) dp[start][end] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
            if (dp[s][e]) sb.append("1\n");
            else sb.append("0\n");
        }

        System.out.print(sb);
    }
}