import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[len];

        for (int i = 0; i < len; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int max = -1;
        for (int num : dp) {
            max = Math.max(max, num);
        }

        System.out.println(max);
    }
}