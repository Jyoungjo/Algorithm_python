import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static int[] arr;

    public static int solution() {
        Map<Integer, Integer> map = new HashMap<>();

        int answer = 0;
        int left = 0, right = 0;
        while (right <= N - 1) {
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);

            if (map.get(arr[right]) > K) {
                while (map.get(arr[right]) > K) {
                    map.put(arr[left], map.get(arr[left]) - 1);
                    left++;
                }
            }

            answer = Math.max(answer, right - left + 1);
            right++;
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }
}