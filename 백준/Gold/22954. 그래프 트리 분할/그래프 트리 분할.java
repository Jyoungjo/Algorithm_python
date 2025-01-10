import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int point;
    int edge;

    public Node(int point, int edge) {
        this.point = point;
        this.edge = edge;
    }
}

public class Main {
    static int N, M;
    static List<List<Node>> graph = new ArrayList<>();
    static boolean[] visited = new boolean[N + 1];
    static List<Integer> nodePath;
    static List<Integer> edgePath;
    static String IMPOSSIBLE = "-1";
    static StringBuilder sb = new StringBuilder();

    public static String solution() {
        // 정점 개수가 1, 2개 일 경우 트리로 분할할 수 없다.
        if (N <= 2) return IMPOSSIBLE;

        int cnt = 0;
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;

            // 여기까지 왔을 때, 카운트가 2라면 세번째 그래프를 탐색해야하는 상황
            // 그래프가 3개 이상일 경우, 분할할 수 없다.
            if (cnt == 2) return IMPOSSIBLE;

            cnt++;
            nodePath = new ArrayList<>();
            edgePath = new ArrayList<>();
            nodePath.add(i);
            // 그래프 탐색 시작
            visited[i] = true;
            dfs(i);

            // 탐색 후, 간선의 개수가 정점 개수 - 1 이라면 그래프가 하나만 존재하는 것
            // 이 경우 N번째 정점과 나머지로 분할하여 리턴하면 끝
            if (edgePath.size() == N - 1) {
                getAnswer();
                return sb.toString();
            }

            // 그래프가 1개가 아니라면 그래프는 2개 이상임이 자명함.
            // 그럼 찾은 그래프마다 정점과 간선 기록 시작
            if (cnt == 1) {
                // 지금 찾은 정점의 개수의 2배가 총 정점 개수라면 크기가 같은 그래프이므로 분할 X
                if (nodePath.size() * 2 == N) return IMPOSSIBLE;
                // 어차피 위 조건분기들로 인해 그래프 2개만 나올 것이므로 미리 트리 개수 계산
                sb.append(nodePath.size()).append(" ").append(N - nodePath.size()).append("\n");
            }

            for (int point : nodePath) sb.append(point).append(" ");
            sb.append("\n");
            for (int edge : edgePath) sb.append(edge).append(" ");
            sb.append("\n");
        }

        return sb.toString();
    }

    private static void getAnswer() {
        sb.append(N - 1).append(" ").append(1).append("\n");
        for (int i = 0; i < nodePath.size() - 1; i++) {
            sb.append(nodePath.get(i)).append(" ");
        }
        sb.append("\n");
        for (int i = 0; i < edgePath.size() - 1; i++) {
            sb.append(edgePath.get(i)).append(" ");
        }
        sb.append("\n");
        sb.append(nodePath.get(nodePath.size() - 1));
    }

    private static void dfs(int num) {
        for (Node node : graph.get(num)) {
            if (visited[node.point]) continue;
            visited[node.point] = true;
            nodePath.add(node.point);
            edgePath.add(node.edge);
            dfs(node.point);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Node(e, i));
            graph.get(e).add(new Node(s, i));
        }

        System.out.print(solution());
    }
}