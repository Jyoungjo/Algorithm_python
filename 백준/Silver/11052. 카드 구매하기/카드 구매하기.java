import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] card_packs = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) card_packs[i] = Integer.parseInt(st.nextToken());

        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) { // Pack
            for (int j = 1; j <= N; j++) { // Total
                if (i <= j) dp[j] = Math.max(dp[j - i] + card_packs[i], dp[j]);
            }
        }

        System.out.println(dp[N]);
    }
}