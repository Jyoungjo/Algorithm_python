import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, n;
    static long[] dp = new long[101];
    static int[] sticks = new int[]{1,7,4,2,0,8};

    public static String solution() {
        // 최솟값 찾기
        for (int i = 9; i <= n; i++) {
            for (int j = 2; j < 8; j++) {
                String tmp = String.valueOf(dp[i - j]) + String.valueOf(sticks[j - 2]);
                dp[i] = Math.min(Long.parseLong(tmp), dp[i]);
            }
        }

        // 최댓값 찾기
        StringBuilder sb = new StringBuilder();
        if (n % 2 == 1) sb.append(7);
        else sb.append(1);

        for (int i = 1; i < n / 2; i++) {
            sb.append(1);
        }

        return dp[n] + " " + sb;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        Arrays.fill(dp, Long.MAX_VALUE);
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;
        dp[8] = 10;

        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            System.out.println(solution());
        }
    }
}