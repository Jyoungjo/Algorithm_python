import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, X;
    static int[] days;

    public static String solution() {
        int left = 0, right = 1;
        int answer = 0, cnt = 0;
        int[] partialSum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            partialSum[i] = partialSum[i - 1] + days[i - 1];
        }

        while (left < N && right < N + 1) {
            if (right - left <= X) {
                if (right - left == X) {
                    if (partialSum[right] - partialSum[left] > answer) {
                        answer = partialSum[right] - partialSum[left];
                        cnt = 1;
                    } else if (partialSum[right] - partialSum[left] == answer) {
                        cnt++;
                    }
                }
                right++;
            } else if (right - left > X) {
                left++;
            }
        }

        if (answer == 0) return "SAD";

        return answer + "\n" + cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        days = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            days[i] = Integer.parseInt(st.nextToken());
        }

        System.out.print(solution());
    }
}