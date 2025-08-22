import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

class Result {
    int min, max;

    public Result(int min, int max) {
        this.min = min;
        this.max = max;
    }
}

class Main {
    final char PLUS = '+', MINUS = '-', MULTIPLE = '*';

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int numCnt = (N + 1) / 2;
        Result[][] dp = new Result[N + 1][N + 1];
        for (int len = 1; len <= numCnt; len++) {
            for (int s = 0; s < N - (len - 1) * 2; s += 2) {
                if (len == 1) {
                    int num = Character.getNumericValue(str.charAt(s));
                    dp[s][s] = new Result(num, num);
                    continue;
                }

                int e = s + (len - 1) * 2, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
                for (int m = s; m < e; m += 2) {
                    if (str.charAt(m + 1) == PLUS) {
                        max = Math.max(max, dp[s][m].max + dp[m + 2][e].max);
                        min = Math.min(min, dp[s][m].min + dp[m + 2][e].min);
                    } else if (str.charAt(m + 1) == MINUS) {
                        max = Math.max(max, dp[s][m].max - dp[m + 2][e].min);
                        min = Math.min(min, dp[s][m].min - dp[m + 2][e].max);
                    } else if (str.charAt(m + 1) == MULTIPLE) {
                        max = Math.max(max, dp[s][m].max * dp[m + 2][e].max);
                        min = Math.min(min, dp[s][m].max * dp[m + 2][e].max);

                        max = Math.max(max, dp[s][m].max * dp[m + 2][e].min);
                        min = Math.min(min, dp[s][m].max * dp[m + 2][e].min);

                        max = Math.max(max, dp[s][m].min * dp[m + 2][e].max);
                        min = Math.min(min, dp[s][m].min * dp[m + 2][e].max);

                        max = Math.max(max, dp[s][m].min * dp[m + 2][e].min);
                        min = Math.min(min, dp[s][m].min * dp[m + 2][e].min);
                    }
                }

                dp[s][e] = new Result(min, max);
            }
        }

        System.out.println(dp[0][N - 1].max);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}