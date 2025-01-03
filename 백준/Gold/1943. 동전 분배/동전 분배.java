import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Coin {
        int value;
        int amount;

        public Coin(int value, int amount) {
            this.value = value;
            this.amount = amount;
        }
    }
    static int N;
    static int sum;
    static Coin[] coins;

    public static int solution() {
        if (sum % 2 == 1) return 0;

        sum /= 2;
        boolean[][] dp = new boolean[N + 1][sum + 1];
        dp[0][0] = true;

        for (int i = 1; i <= N; i++) {
            Coin coin = coins[i - 1];
            for (int j = 0; j <= sum; j++) {
                if (dp[i - 1][j]) {
                    for (int k = 0; k <= coin.amount; k++) {
                        int tmp = j + coin.value * k;
                        if (tmp <= sum) dp[i][tmp] = true;
                    }
                }
            }
        }

        if (dp[N][sum]) return 1;
        else return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 3; i++) {
            N = Integer.parseInt(br.readLine());
            coins = new Coin[N];
            sum = 0;
            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                coins[j] = new Coin(v, a);
                sum += v * a;
            }
            System.out.println(solution());
        }
    }
}