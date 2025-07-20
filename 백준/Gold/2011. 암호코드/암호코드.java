import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] password = br.readLine().toCharArray();
        int len = password.length;
        final int MOD = 1000000;

        int[] dp = new int[len + 1];
        dp[0] = 1;
        for (int i = 1; i <= len; i++) {
            int currentNum = password[i - 1] - '0';
            if (1 <= currentNum && currentNum <= 9) dp[i] += (dp[i - 1] % MOD);
            if (i == 1) continue;

            int prevNum = password[i - 2] - '0';
            if (prevNum == 0) continue;
            int combNum = prevNum * 10 + currentNum;
            if (10 <= combNum && combNum <= 26) dp[i] += (dp[i - 2] % MOD);
        }

        System.out.println(dp[len] % MOD);
    }
}