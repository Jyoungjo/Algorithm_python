import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] space;
    static int answer = Integer.MAX_VALUE;
    static int[] dx = {-1, 0, 1};

    public static int solution() {
        // 시작 경로부터 최소 연료값 탐색
        // 그 다음 과정부터 세 가지 방향 중 이전에 진행한 방향이 아니면서 최소인 값 탐색
        // 연료의 합이 최소가 되는 방안 구해야함
        // dfs 진행하면서 탐색 완료될 때마다 최소값인지 판단하고 갱신

        for (int i = 0; i < M; i++) {
            dfs(0, i, 0, -1);
        }

        return answer;
    }

    private static void dfs(int sum, int x, int y, int direction) {
        if (y == N) {
            answer = Math.min(sum, answer);
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (x < 0 || x >= M) return;
            if (direction == i) continue;
            dfs(sum + space[y][x], x + dx[i], y + 1, i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        space = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.print(solution());
    }
}