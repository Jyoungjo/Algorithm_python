import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Area {
    char name;
    int id;

    Area(char name, int id) {
        this.name = name;
        this.id = id;
    }

    void change(char newName, int newId) {
        this.name = newName;
        this.id = newId;
    }
}

class State {
    int r, c;

    State(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Info {
    int r, c, dist;

    Info(int r, int c, int dist) {
        this.r = r;
        this.c = c;
        this.dist = dist;
    }
}

class Main {
    final char ISLAND = 'X', SHALLOW = 'S', DEEP = '.';
    final int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    int R, C, islandsCnt = 0;
    char[][] map;
    Area[][] areas;
    int[][] dist, dp;
    Map<Integer, List<int[]>> islandMap = new HashMap<>();

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        areas = new Area[R][C];

        for (int i = 0; i < R; i++) map[i] = br.readLine().trim().toCharArray();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) areas[i][j] = new Area(DEEP, 0);
        }

        numberingIslandAndShallow();
        calMinDist();
        System.out.println(calAnswer());
    }

    private int calAnswer() {
        int visit = 1 << islandsCnt;
        dp = new int[visit][islandsCnt + 1];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);

        for (int i = 0; i < islandsCnt; i++) dp[1 << i][i + 1] = 0;

        for (int mask = 0; mask < (1 << islandsCnt); mask++) {
            for (int last = 0; last < islandsCnt; last++) {
                if ((mask & (1 << last)) == 0 || dp[mask][last + 1] == Integer.MAX_VALUE) continue;
                for (int next = 0; next < islandsCnt; next++) {
                    if ((mask & (1 << next)) != 0) continue; // 이미 다음 섬을 방문 했다면 넘어감
                    dp[mask | (1 << next)][next + 1] =
                            Math.min(
                                    dp[mask | (1 << next)][next + 1],
                                    dp[mask][last + 1] + dist[last + 1][next + 1]
                            );
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= islandsCnt; i++) answer = Math.min(answer, dp[(1 << islandsCnt) - 1][i]);
        return answer;
    }

    private void calMinDist() {
        dist = new int[islandsCnt + 1][islandsCnt + 1];
        for (int i = 1; i <= islandsCnt; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        for (int s = 1; s < islandsCnt; s++) {
            for (int e = s + 1; e <= islandsCnt; e++) calDist(s, e);
        }
    }

    private void calDist(int start, int end) {
        int[] s = islandMap.get(start).get(0);
        Deque<Info> q = new ArrayDeque<>();
        q.add(new Info(s[0], s[1], 0));
        boolean[][] visited = new boolean[R][C];
        visited[s[0]][s[1]] = true;

        while (!q.isEmpty()) {
            Info now = q.poll();

            if (areas[now.r][now.c].name == ISLAND && areas[now.r][now.c].id == end) {
                dist[start][end] = Math.min(dist[start][end], now.dist);
                dist[end][start] = Math.min(dist[end][start], now.dist);
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d], nc = now.c + dc[d];
                if (!isInRange(nr, nc) || visited[nr][nc] || map[nr][nc] == DEEP) continue;

                visited[nr][nc] = true;
                if (map[nr][nc] == ISLAND) q.addFirst(new Info(nr, nc, now.dist));
                else q.addLast(new Info(nr, nc, now.dist + 1));
            }
        }
    }

    private void numberingIslandAndShallow() {
        int id = 0;
        boolean[][] visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && map[i][j] == ISLAND) bfs(i, j, ++id, visited, ISLAND);
            }
        }
        islandsCnt = id;

        id = 1;
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && map[i][j] == SHALLOW) bfs(i, j, id++, visited, SHALLOW);
            }
        }
    }

    private void bfs(int sr, int sc, int id, boolean[][] visited, char target) {
        Queue<State> q = new LinkedList<>();
        q.add(new State(sr, sc));
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            State now = q.poll();
            areas[now.r][now.c].change(target, id);
            if (target == ISLAND) {
                islandMap.putIfAbsent(id, new ArrayList<>());
                islandMap.get(id).add(new int[]{now.r, now.c});
            }

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d], nc = now.c + dc[d];
                if (!isInRange(nr, nc) || visited[nr][nc] || map[nr][nc] != target) continue;
                visited[nr][nc] = true;
                q.add(new State(nr, nc));
            }
        }
    }

    private boolean isInRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}