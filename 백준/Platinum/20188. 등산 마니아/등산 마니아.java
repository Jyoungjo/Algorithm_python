import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] nodeCnt;

    public static long solution() {
        long cnt = 0;
        dfs(1, 1);
        for (int i = 2; i <= N; i++) {
            cnt += count(N) - count(N - nodeCnt[i]);
        }
        return cnt;
    }

    private static long count(int x) {
        return (long) x * (x - 1) / 2;
    }

    private static int dfs(int parent, int child) {
        nodeCnt[child]++;
        for (int node : graph.get(child)) {
            if (parent == node) continue;
            nodeCnt[child] += dfs(child, node);
        }
        return nodeCnt[child];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph.get(s).add(e);
            graph.get(e).add(s);
        }

        nodeCnt = new int[N + 1];

        System.out.print(solution());
    }
}