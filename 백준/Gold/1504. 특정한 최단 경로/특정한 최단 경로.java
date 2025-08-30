import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
    int to, dist;

    Edge(int to, int dist) {
        this.to = to;
        this.dist = dist;
    }
}

class State implements Comparable<State> {
    int node, tot;

    State(int node, int tot) {
        this.node = node;
        this.tot = tot;
    }

    @Override
    public int compareTo(State o) {
        return Integer.compare(this.tot, o.tot);
    }
}

class Main {
    int N, E;
    List<Edge>[] edges;
    int[] dists;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edges = new List[N + 1];
        for (int i = 1; i <= N; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            edges[start].add(new Edge(end, dist));
            edges[end].add(new Edge(start, dist));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken()), v2 = Integer.parseInt(st.nextToken());

        int min1 = 0, min2 = 0;
        boolean isCross1 = true, isCross2 = true;
        
        // 1 -> v1 -> v2 -> N
        // 1 -> v2 -> v1 -> N
        dijkstra(1);
        if (dists[v1] == Integer.MAX_VALUE) isCross1 = false; 
        else min1 += dists[v1];
        if (dists[v2] == Integer.MAX_VALUE) isCross2 = false;
        else min2 += dists[v2];

        dijkstra(v1);
        if (dists[v2] == Integer.MAX_VALUE) isCross1 = false;
        else min1 += dists[v2];
        if (dists[v2] == Integer.MAX_VALUE) isCross2 = false;
        else min2 += dists[v2];

        dijkstra(N);
        if (dists[v2] == Integer.MAX_VALUE) isCross1 = false;
        else min1 += dists[v2];
        if (dists[v1] == Integer.MAX_VALUE) isCross2 = false;
        else min2 += dists[v1];

        if (!isCross1 && !isCross2) System.out.println(-1);
        else if (!isCross2) System.out.println(min1);
        else if (!isCross1) System.out.println(min2);
        else System.out.println(Math.min(min1, min2));
    }

    private void dijkstra(int start) {
        Queue<State> pq = new PriorityQueue<>();
        pq.add(new State(start, 0));
        dists = new int[N + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);
        dists[start] = 0;

        while (!pq.isEmpty()) {
            State now = pq.poll();
            if (now.tot > dists[now.node]) continue;

            for (Edge next : edges[now.node]) {
                if (dists[next.to] > now.tot + next.dist) {
                    dists[next.to] = now.tot + next.dist;
                    pq.add(new State(next.to, now.tot + next.dist));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}