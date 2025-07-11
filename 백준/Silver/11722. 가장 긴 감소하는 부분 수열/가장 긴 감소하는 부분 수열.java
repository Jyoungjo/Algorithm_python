import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[N + 1];
        for (int i = 1; i <= N; i++) numbers[i] = Integer.parseInt(st.nextToken());

        int[] dp = new int[N + 1];
        for (int i = N; i >= 1; i--) {
            dp[i] = 1;
            for (int j = N; j > i; j--) {
                if (numbers[i] > numbers[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int max = -1;
        for (int n : dp) max = Math.max(max, n);

        System.out.println(max);
    }
}