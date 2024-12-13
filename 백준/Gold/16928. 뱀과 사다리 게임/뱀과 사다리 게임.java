import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static Map<Integer, Integer> ladders;
    static Map<Integer, Integer> snakes;

    public static int solution() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 0});
        boolean[] visited = new boolean[101];
        visited[1] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int pos = current[0];
            int cnt = current[1];

            for (int i = 6; i >= 1; i--) {
                int next = pos + i;
                if (next > 100) continue;
                if (next == 100) {
                    return cnt + 1;
                }

                if (ladders.containsKey(next)) {
                    next = ladders.get(next);
                }

                if (snakes.containsKey(next)) {
                    next = snakes.get(next);
                }

                if (!visited[next]) {
                    visited[next] = true;
                    q.add(new int[]{next, cnt + 1});
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ladders = new HashMap<>();
        snakes = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ladders.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            snakes.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(solution());
    }
}