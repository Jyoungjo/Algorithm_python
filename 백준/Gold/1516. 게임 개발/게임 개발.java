import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] times = new int[N + 1];
        int[] inDegree = new int[N + 1];
        List<Integer>[] buildings = new List[N + 1];
        for (int i = 1; i <= N; i++) buildings[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            String[] arr = br.readLine().split(" ");
            for (int j = 0; j < arr.length - 1; j++) {
                int num = Integer.parseInt(arr[j]);
                if (j == 0) times[i] = num;
                else {
                    inDegree[i]++;
                    buildings[num].add(i);
                }
            }
        }

        int[] dp = new int[N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
                dp[i] = times[i];
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

        for (int i = 1; i <= N; i++) System.out.println(dp[i]);
    }
}