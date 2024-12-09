import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static int N, D;
    static List<List<Node>> graph;

    public static int solution() {
        int[] dist = new int[D + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        pq.add(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int curPos = current.node;
            int curDist = current.weight;

            if (curPos + 1 <= D && curDist + 1 < dist[curPos + 1]) {
                dist[curPos + 1] = curDist + 1;
                pq.add(new Node(curPos + 1, curDist + 1));
            }

            for (Node next : graph.get(curPos)) {
                int nextPos = next.node;
                int nextDist = curDist + next.weight;

                if (nextDist < dist[nextPos]) {
                    dist[nextPos] = nextDist;
                    pq.add(new Node(nextPos, nextDist));
                }
            }
        }

        return dist[D];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= D; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (e <= D) {
                graph.get(s).add(new Node(e, w));
            }
        }

        System.out.println(solution());
    }
}