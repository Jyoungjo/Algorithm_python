import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    private int y, x;

    Point(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() { return this.y; }
    public int getX() { return this.x; }
}

class Main {
    final int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    int N, K, R, answer = 0;
    List<Point>[][] farm;
    boolean[][] cows;

    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        farm = new List[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) farm[i][j] = new ArrayList<>();
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            farm[y1][x1].add(new Point(y2, x2));
            farm[y2][x2].add(new Point(y1, x1));
        }

        cows = new boolean[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            cows[y][x] = true;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (cows[i][j]) bfs(i, j);
            }
        }

        System.out.println(answer / 2);
    }

    private void bfs(int startY, int startX) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startY, startX});
        boolean[][] visited = new boolean[N + 1][N + 1];
        visited[startY][startX] = true;

        int cnt = -1; // 길을 건너지 않고 만날 수 있는 소의 경우의 수
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int y = now[0], x = now[1];

            if (cows[y][x]) cnt++;

            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d], nx = x + dx[d];
                if (!isInRange(ny, nx) || visited[ny][nx]) continue;

                // 현재 좌표와 연결된 길들을 순회하면서 내가 다음에 나아갈 방향에 길이 있는지 체크
                boolean isNotRoad = true;
                for (Point way : farm[y][x]) {
                    // 순회 중 다음으로 나아갈 방향에 길이 존재한다면 그 방향으로는 진행 불가
                    if (way.getY() == ny && way.getX() == nx) {
                        isNotRoad = false;
                        break;
                    }
                }

                // 길이 아닐때만 bfs 진행
                if (isNotRoad) {
                    visited[ny][nx] = true;
                    q.add(new int[]{ny, nx});
                }
            }
        }

        answer += K - 1 - cnt; // 전체 소의 수 - 시작 소 - 찾은 소 = 길을 건너지 않으면 만날 수 없는 소
    }

    private boolean isInRange(int y, int x) {
        return 0 < y && y <= N && 0 < x && x <= N;
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}