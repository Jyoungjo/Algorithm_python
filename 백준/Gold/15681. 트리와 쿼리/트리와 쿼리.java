import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    List<Integer>[] tree;
    boolean[] visited;
    int[] dp;

    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), R = Integer.parseInt(st.nextToken()), Q = Integer.parseInt(st.nextToken());
        tree = new List[N + 1];
        for (int i = 1; i <= N; i++) tree[i] = new ArrayList<>();

        for (int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
            tree[s].add(e);
            tree[e].add(s);
        }

        dp = new int[N + 1];
        visited = new boolean[N + 1];
        solve(R);
        for (int i = 0; i < Q; i++) System.out.println(dp[Integer.parseInt(br.readLine())]);
    }

    private void solve(int now) {
        visited[now] = true;
        dp[now] = 1;
        for (int next : tree[now]) {
            if (visited[next]) continue;
            solve(next);
            dp[now] += dp[next];
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}