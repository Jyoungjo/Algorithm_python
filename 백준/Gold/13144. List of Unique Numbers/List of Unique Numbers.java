import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;

    public static long solution() {
        long answer = 0;
        Set<Integer> set = new HashSet<>();
        int l = 0, r = 0;

        while (l < N) {
            while (r < N && !set.contains(arr[r])) {
                set.add(arr[r++]);
            }

            answer += (r - 1) - l + 1;
            set.remove(arr[l++]);
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

        System.out.println(solution());
    }
}