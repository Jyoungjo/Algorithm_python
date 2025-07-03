import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[N + 1];
        for (int i = 1; i <= N; i++) numbers[i] = Integer.parseInt(st.nextToken());

        int[] dp = new int[N + 1];
        dp[1] = numbers[1];
        for (int i = 1; i <= N; i++) {
            dp[i] = numbers[i];
            for (int j = 1; j < i; j++) {
                if (numbers[j] < numbers[i]) dp[i] = Math.max(dp[j] + numbers[i], dp[i]);
            }
        }

        int max = -1;
        for (int num : dp) max = Math.max(max, num);

        System.out.println(max);
    }
}