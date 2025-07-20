import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[] dp = new boolean[N + 1];
        dp[0] = true;
        for (int i = 1; i <= N; i++) {
            if (i - 1 >= 0) dp[i] = !dp[i - 1];
            if (i - 3 >= 0) dp[i] = !dp[i - 3];
        }
        System.out.println(dp[N] ? "SK" : "CY");
    }
}