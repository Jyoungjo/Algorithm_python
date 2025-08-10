import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    List<int[]>[] network;
    int[] dist;
    int N;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        network = new List[N + 1];
        for (int i = 1; i <= N; i++) network[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken()), q = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
            network[p].add(new int[]{q, r});
            network[q].add(new int[]{p, r});
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
            dist = new int[N + 1];
            for (int j = 1; j <= N; j++) dist[j] = Integer.MAX_VALUE;
            bfs(v);

            int result = 0;
            for (int j = 1; j <= N; j++) if (j != v && dist[j] >= k) result++;
            System.out.println(result);
        }
    }

    private void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        boolean[] visited = new boolean[N + 1];

        while (!q.isEmpty()) {
            int now = q.poll();
            visited[now] = true;

            for (int[] next : network[now]) {
                int n_vertex = next[0], n_d = next[1];
                if (visited[n_vertex]) continue;

                dist[n_vertex] = Math.min(dist[now], n_d);
                q.add(n_vertex);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}