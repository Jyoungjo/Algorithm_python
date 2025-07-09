import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
        for (int t = 0; t < T; t++) {
            // N, K
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());

            int[] times = new int[N + 1];
            List<Integer>[] buildings = new List[N + 1];
            int[] inDegree = new int[N + 1];

            // 건물 짓는데 걸리는 시간
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                times[i] = Integer.parseInt(st.nextToken());
                buildings[i] = new ArrayList<>();
            }

            // 건설 순서
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
                buildings[s].add(e);
                inDegree[e]++;
            }

            // 찾는 건물
            int W = Integer.parseInt(br.readLine());

            dp = new int[N + 1];
            play(N, times, buildings, inDegree);

            System.out.println(dp[W]);
        }
    }

    private static void play(int N, int[] times, List<Integer>[] buildings, int[] inDegree) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                dp[i] = times[i];
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : buildings[now]) {
                dp[next] = Math.max(dp[now] + times[next], dp[next]);
                inDegree[next]--;
                if (inDegree[next] == 0) q.add(next);
            }
        }
    }
}