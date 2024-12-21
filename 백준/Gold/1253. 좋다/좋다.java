import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;

    public static int solution() {
        int answer = 0;

        for (int i = 0; i < N; i++) { // O(N)
            int target = arr[i];
            boolean flag = false;

            for (int j = 0; j < N; j++) { // O(N)
                if (i == j) continue;

                int left = 0, right = N - 1;
                while (left <= right) { // O(logN)
                    int mid = (left + right) / 2;

                    if (target > arr[mid] + arr[j]) {
                        left = mid + 1;
                    } else if (target == arr[mid] + arr[j] && mid != i && mid != j) {
                        flag = true;
                        break;
                    } else {
                        right = mid - 1;
                    }
                }
            }

            if (flag) answer++;
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        System.out.println(solution());
    }
}