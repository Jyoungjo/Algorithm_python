import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int[] now;
        int cnt;
        boolean isCrashed;

        public Node(int[] now, int cnt, boolean isCrashed) {
            this.now = now;
            this.cnt = cnt;
            this.isCrashed = isCrashed;
        }
    }
    static int N, M;
    static char[][] map;
    static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};

    public static int solution() {
        // 벽을 한번만 부술수 있고 이를 지나갈 최소 거리 측정
        // bfs 진행하는데 벽이 있는 경우와 없는 경우 탐색
        // 있을 경우 범위 내에서 벽 부술수 있는 횟수가 남았는지 아닌지
        // 없을 경우 범위 내에서 지나갈 수 있는지 확인
        // 벽을 처음에 부순다고 무조건 최단 거리가 되는 것은 아니기 때문에 깼을 때와 안 깨고 나아갈 때
        // 목적지의 최단거리를 갱신한다.

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(new int[]{0, 0}, 1, false));
        boolean[][][] visited = new boolean[N][M][2];
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node current = q.poll();
            int y = current.now[0], x = current.now[1];

            if (y == N - 1 && x == M - 1) return current.cnt;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];

                if (!isWithinRange(nx, ny) || (ny == y && nx == x)) continue;

                int nextCnt = current.cnt + 1;

                if (map[ny][nx] == '1') {
                    if (!current.isCrashed) {
                        q.add(new Node(new int[]{ny, nx}, nextCnt, true));
                        visited[ny][nx][1] = true;
                    }
                } else {
                    if (!current.isCrashed && !visited[ny][nx][0]) {
                        q.add(new Node(new int[]{ny, nx}, nextCnt, false));
                        visited[ny][nx][0] = true;
                    } else if (current.isCrashed && !visited[ny][nx][1]) {
                        q.add(new Node(new int[]{ny, nx}, nextCnt, true));
                        visited[ny][nx][1] = true;
                    }
                }
            }
        }

        return -1;
    }

    private static boolean isWithinRange(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(solution());
    }
}