import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str1 = br.readLine().toCharArray(), str2 = br.readLine().toCharArray();
        int l1 = str1.length, l2 = str2.length;
        int[][] dp = new int[l1 + 1][l2 + 1];

        int max = 0;
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        System.out.println(max);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}