import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = dp[1] = 0;
        int[] nums = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
                nums[i] = i / 3;
            }
            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
                nums[i] = i / 2;
            }
            if (i - 1 > 0 && dp[i] > dp[i - 1] + 1) {
                dp[i] = dp[i - 1] + 1;
                nums[i] = i - 1;
            }
        }
        System.out.println(dp[N]);

        StringBuilder sb = new StringBuilder();
        int num = N;
        while (num >= 1) {
            sb.append(num).append(" ");
            num = nums[num];
        }
        System.out.print(sb);
    }
}