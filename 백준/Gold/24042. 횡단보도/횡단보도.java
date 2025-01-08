import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int loc;
        long time;

        public Node(int loc, long time) {
            this.loc = loc;
            this.time = time;
        }
    }
    static int N, M;
    static List<List<Node>> graph = new ArrayList<>();

    public static long solution() {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(a -> a.time));
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.loc] < cur.time) continue;

            for (Node next : graph.get(cur.loc)) {
                long nextTime = (long) (Math.ceil((double) (cur.time - next.time) / M)) * M + next.time + 1;

                if (dist[next.loc] > nextTime) {
                    dist[next.loc] = nextTime;
                    pq.add(new Node(next.loc, nextTime));
                }
            }
        }

        return dist[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Node(e, i));
            graph.get(e).add(new Node(s, i));
        }

        System.out.println(solution());
    }
}