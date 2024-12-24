import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] cities;
    static int[] plans;

    public static String solution() {
        int start = plans[0];
        for (int i = 1; i < M; i++) {
            int end = plans[i];
            
            if (start == end) continue;

            if (!bfs(start, end)) {
                return "NO";
            }
            
            start = end;
        }

        return "YES";
    }

    private static boolean bfs(int now, int next) {
        Queue<Integer> q = new LinkedList<>();
        q.add(now);

        boolean[] visited = new boolean[N + 1];
        visited[now] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 1; i <= N; i++) {
                if (cities[cur][i] == 1) {
                    if (i == next) return true;

                    if (!visited[i]) {
                        visited[i] = true;
                        q.add(i);
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        cities = new int[N + 1][N + 1];

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                cities[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        plans = new int[M];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            plans[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }
}