import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] boxes = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) boxes[i] = Integer.parseInt(st.nextToken());

        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (boxes[i] > boxes[j]) dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
        }

        int max = -1;
        for (int i = 0; i < N; i++) max = Math.max(max, dp[i]);
        System.out.println(max);
    }
}