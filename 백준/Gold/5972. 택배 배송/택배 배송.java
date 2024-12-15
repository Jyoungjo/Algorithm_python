import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int current;
        int cost;

        public Node(int current, int cost) {
            this.current = current;
            this.cost = cost;
        }
    }
    static int N, M;
    static List<List<int[]>> roads;

    public static int solution() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int cur = node.current, cost = node.cost;

            for (int[] arr : roads.get(cur)) {
                int next = arr[0], nextCost = arr[1];
                if (dist[next] > cost + nextCost) {
                    dist[next] = cost + nextCost;
                    pq.add(new Node(next, cost + nextCost));
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

        roads = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            roads.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            roads.get(start).add(new int[]{end, cost});
            roads.get(end).add(new int[]{start, cost});
        }

        System.out.println(solution());
    }
}