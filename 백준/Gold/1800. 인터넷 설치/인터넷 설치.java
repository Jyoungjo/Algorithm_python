import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    int N, P, K;
    List<int[]>[] cables;
    int[] dist;

    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cables = new List[N + 1];
        dist = new int[N + 1];
        for (int i = 1; i <= N; i++) cables[i] = new ArrayList<>();

        int l = 0, r = 0;
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            cables[s].add(new int[]{e, cost});
            cables[e].add(new int[]{s, cost});
            r = Math.max(r, cost);
        }

        int answer = -1;
        while (l <= r) {
            int mid = (l + r) / 2;

            if (dijkstra(mid)) {
                answer = mid;
                r = mid - 1;
            } else l = mid + 1;
        }

        System.out.println(answer);
    }

    private boolean dijkstra(int payment) {
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparing(o -> o[1]));
        pq.add(new int[]{1, 0});
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0], cnt = cur[1];

            if (dist[node] < cnt) continue;

            for (int[] next : cables[node]) {
                int val = cnt;
                if (next[1] > payment) val++;
                
                if (dist[next[0]] > val) {
                    dist[next[0]] = val;
                    pq.add(new int[]{next[0], val});
                }
            }
        }

        return dist[N] <= K;
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}