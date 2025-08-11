import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
        int[][] nodes = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nodes[i][0] = Integer.parseInt(st.nextToken());
            nodes[i][1] = Integer.parseInt(st.nextToken());
        }

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        boolean[] visited = new boolean[N];
        pq.add(new int[]{0, 0});

        int answer = 0, edgeCnt = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now = cur[0], val = cur[1];

            if (visited[now]) continue;

            visited[now] = true;
            answer += val;
            edgeCnt++;

            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    int length = getEuclideanLength(nodes[now][0], nodes[now][1], nodes[i][0], nodes[i][1]);
                    if (length >= C) pq.add(new int[]{i, length});
                }
            }
        }

        System.out.println(edgeCnt == N ? answer : -1);
    }

    private int getEuclideanLength(int y1, int x1, int y2, int x2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}