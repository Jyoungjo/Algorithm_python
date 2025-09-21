import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class State implements Comparable<State> {
    int y, x, move, gravity;

    State(int y, int x, int move, int gravity) {
        this.y = y;
        this.x = x;
        this.move = move;
        this.gravity = gravity;
    }

    @Override
    public int compareTo(State o) {
        return Integer.compare(this.move, o.move);
    }
}

class Main {
    final int[] dx = {-1, 1};
    final char WALL = '#', CAPTAIN = 'C', DOCTOR = 'D';
    final int MAX = Integer.MAX_VALUE;
    int R, C;
    char[][] map;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][];
        for (int i = 0; i < R; i++) map[i] = br.readLine().trim().toCharArray();

        int[] start = findStartingPoint();
        // CAPTAIN의 시작이 공중에 있을수도 있으므로 중력 영향 받게 위치 조정 필요함
        start[0] = moveByGravity(start[0], start[1], 0);
        if (start[0] == -1) {
            System.out.println(0);
            return;
        }

        if (start[0] == MAX) {
            System.out.println(-1);
            return;
        }

        System.out.println(dijkstra(start));
    }

    private int dijkstra(int[] start) {
        Queue<State> pq = new PriorityQueue<>();
        pq.add(new State(start[0], start[1], 0, 0));
        boolean[][][] visited = new boolean[R][C][2];
        visited[start[0]][start[1]][0] = true;

        while (!pq.isEmpty()) {
            State now = pq.poll();

            for (int d = 0; d < 2; d++) {
                int nx = now.x + dx[d];
                if (nx < 0 || nx >= C) continue;
                if (map[now.y][nx] != WALL) {
                    int ny = moveByGravity(now.y, nx, now.gravity);

                    if (ny == -1) return now.move;
                    if (ny != MAX && !visited[ny][nx][now.gravity]) {
                        visited[ny][nx][now.gravity] = true;
                        pq.add(new State(ny, nx, now.move, now.gravity));
                    }
                }
            }

            int grav = (now.gravity + 1) % 2;
            int ny = moveByGravity(now.y, now.x, grav);
            if (ny == -1) return now.move + 1;

            if (ny != MAX && !visited[ny][now.x][grav]) {
                visited[ny][now.x][grav] = true;
                pq.add(new State(ny, now.x, now.move + 1, grav));
            }
        }

        return -1;
    }

    private int moveByGravity(int sy, int sx, int gravity) {
        if (gravity == 0) {
            for (int ny = sy; ny < R; ny++) {
                if (map[ny][sx] == WALL) return ny - 1;
                if (map[ny][sx] == DOCTOR) return -1;
            }
        } else {
            for (int ny = sy; ny >= 0; ny--) {
                if (map[ny][sx] == WALL) return ny + 1;
                if (map[ny][sx] == DOCTOR) return -1;
            }
        }

        return MAX;
    }

    private int[] findStartingPoint() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == CAPTAIN) return new int[]{i, j};
            }
        }

        return new int[]{0, 0};
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}