import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long A, B;
    static long[] dp;

    public static long solution() {
        setDp();
        return cal(B) - cal(A - 1);
    }

    private static long cal(long N) {
        long cnt = N & 1;
        int size = (int) (Math.log(N) / Math.log(2));

        for (int i = size; i > 0; i--) {
            if ((N & (1L << i)) != 0L) {
                cnt += dp[i - 1] + 1 + (N - (1L << i));
                N -= (1L << i);
            }
        }

        return cnt;
    }

    private static void setDp() {
        dp = new long[55];
        dp[0] = 1;

        for (int i = 1; i < 55; i++) {
            dp[i] = (dp[i - 1] << 1) + (1L << i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        System.out.println(solution());
    }
}