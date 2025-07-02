import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(st.nextToken());

        int[] dp1 = new int[N], dp2 = new int[N];
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (numbers[j] < numbers[i]) dp1[i] = Math.max(dp1[i], dp1[j] + 1);
            }
        }

        for (int i = N - 2; i >= 0; i--) {
            for (int j = N - 1; j > i; j--) {
                if (numbers[j] < numbers[i]) dp2[i] = Math.max(dp2[i], dp2[j] + 1);
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) max = Math.max(max, dp1[i] + dp2[i]);

        System.out.println(max - 1);
    }
}