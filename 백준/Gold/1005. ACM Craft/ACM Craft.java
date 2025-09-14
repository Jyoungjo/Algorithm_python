import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    int T, N, K;
    int[] build_time, inDegree, dp;
    List<Integer>[] build_ordered;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            build_time = new int[N + 1];
            inDegree = new int[N + 1];
            dp = new int[N + 1];
            build_ordered = new List[N + 1];
            for (int j = 1; j <= N; j++) {
                build_time[j] = Integer.parseInt(st.nextToken());
                build_ordered[j] = new ArrayList<>();
            }

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                build_ordered[s].add(e);
                inDegree[e]++;
            }

            int W = Integer.parseInt(br.readLine());
            play();
            System.out.println(dp[W]);
        }
    }

    private void play() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                dp[i] = build_time[i];
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : build_ordered[now]) {
                dp[next] = Math.max(dp[next], dp[now] + build_time[next]);
                inDegree[next]--;
                if (inDegree[next] == 0) q.add(next);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}