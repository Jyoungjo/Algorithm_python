import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    List<Integer>[] tree;
    int[][] dp;
    boolean[] visited;

    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new List[N + 1];
        for (int i = 1; i <= N; i++) tree[i] = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
            tree[s].add(e);
            tree[e].add(s);
        }

        dp = new int[N + 1][2];
        visited = new boolean[N + 1];
        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private void dfs(int now) {
        visited[now] = true;
        dp[now][0] = 0; dp[now][1] = 1;

        for (int next : tree[now]) {
            if (visited[next]) continue;
            dfs(next);
            dp[now][0] += dp[next][1];
            dp[now][1] += Math.min(dp[next][0], dp[next][1]);
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}