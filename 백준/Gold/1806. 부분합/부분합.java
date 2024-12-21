import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, S;
    static int[] arr;

    public static int solution() {
        int answer = Integer.MAX_VALUE;

        int[] partialSum = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            partialSum[i] = partialSum[i - 1] + arr[i - 1];
        }

        int l = 0, r = 0;
        while (l <= r && l < N + 1 && r < N + 1) {
            int pSum = partialSum[r] - partialSum[l];

            if (pSum >= S) {
                answer = Math.min(answer, r - l);
                l++;
            } else {
                r++;
            }
        }

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }
}