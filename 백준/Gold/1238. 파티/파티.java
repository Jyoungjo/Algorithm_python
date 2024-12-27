import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, X;
    static List<List<int[]>> list;

    public static int solution() {
        int answer = 0;

        for (int i = 1; i <= N; i++) {
            int times = dijkstra(i, X) + dijkstra(X, i);
            answer = Math.max(times, answer);
        }

        return answer;
    }

    private static int dijkstra(int i, int end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[1]));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new int[]{i, 0});
        dist[i] = 0;

        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int now = data[0], d = data[1];

            for (int[] arr : list.get(now)) {
                int next = arr[0], nextDist = arr[1];
                if (nextDist + d < dist[next]) {
                    dist[next] = nextDist + d;
                    pq.add(new int[]{next, nextDist + d});
                }
            }
        }

        return dist[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            list.get(start).add(new int[]{end, dist});
        }

        System.out.println(solution());
    }
}