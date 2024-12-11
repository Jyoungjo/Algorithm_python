import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;

    public static int solution() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);

        int[] times = new int[100001];
        Arrays.fill(times, Integer.MAX_VALUE);
        times[N] = 0;

        while (!q.isEmpty()) {
            int current = q.poll();

            if (current == K) return times[current];

            int[] op = {current - 1, current + 1, current * 2};

            for (int i = 0; i < 3; i++) {
                int next = op[i], nextTime = times[current] + 1;
                if (i == 2) nextTime = times[current];

                if (0 <= next && next < 100001 && times[next] > nextTime) {
                    q.offer(next);
                    times[next] = nextTime;
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(solution());
    }
}