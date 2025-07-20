import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        BigInteger[] dp = new BigInteger[N + 1];

        for (int i = 0; i <= N; i++) {
            if (i == 0) dp[i] = BigInteger.valueOf(0);
            else if (i == 1) dp[i] = BigInteger.valueOf(1);
            else dp[i] = dp[i - 1].add(dp[i - 2]);
        }

        System.out.println(dp[N]);
    }
}