import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new long[n + 1];
        System.out.print(fibonacci(n));
    }

    private static long fibonacci(int n) {
        if (n <= 1) return dp[n] = n;
        if (dp[n] != 0) return dp[n];
        return dp[n] = fibonacci(n - 1) + fibonacci(n - 2);
    }
}