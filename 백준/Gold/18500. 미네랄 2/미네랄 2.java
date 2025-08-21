import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    final char MINERAL = 'x', BLANK = '.';
    final int[] dy = {1, 0, -1, 0}, dx = {0, 1, 0, -1};
    int R, C;

    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        char[][] minerals = new char[R][C];
        for (int i = 0; i < R; i++) minerals[i] = br.readLine().toCharArray();
        int N = Integer.parseInt(br.readLine());
        int[] heights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < N; i++) {
            int y = R - heights[i];
            if (i % 2 == 0) {
                for (int x = 0; x < C; x++) {
                    if (minerals[y][x] == MINERAL) {
                        dig(minerals, y, x);
                        break;
                    }
                }
            } else {
                for (int x = C - 1; x >= 0; x--) {
                    if (minerals[y][x] == MINERAL) {
                        dig(minerals, y, x);
                        break;
                    }
                }
            }
        }

        for (char[] c : minerals) System.out.println(String.valueOf(c));
    }

    private void dig(char[][] minerals, int y, int x) {
        minerals[y][x] = BLANK;
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> q = new LinkedList<>();
        for (int c = 0; c < C; c++) {
            if (minerals[R - 1][c] == MINERAL) {
                visited[R - 1][c] = true;
                q.add(new int[]{R - 1, c});
            }
        }

        bfs(minerals, q, visited);

        List<int[]> floatings = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (minerals[i][j] == MINERAL && !visited[i][j]) floatings.add(new int[]{i, j});
            }
        }

        if (floatings.isEmpty()) return;

        for (int[] coord : floatings) minerals[coord[0]][coord[1]] = BLANK;

        int depth = calDepth(floatings, minerals);

        for (int[] coord : floatings) minerals[coord[0] + depth][coord[1]] = MINERAL;
    }

    private void bfs(char[][] minerals, Queue<int[]> q, boolean[][] visited) {
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int y = now[0], x = now[1];

            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d], nx = x + dx[d];
                if (!isInRange(ny, nx) || visited[ny][nx] || minerals[ny][nx] == BLANK) continue;
                visited[ny][nx] = true;
                q.add(new int[]{ny, nx});
            }
        }
    }

    private int calDepth(List<int[]> floatings, char[][] minerals) {
        int result = Integer.MAX_VALUE;
        for (int[] coord : floatings) {
            int y = coord[0], x = coord[1];
            int h = 0;
            while (true) {
                int ny = y + h + 1;
                if (ny >= R || minerals[ny][x] == MINERAL) break;
                h++;
            }

            result = Math.min(result, h);
        }

        return result;
    }

    private boolean isInRange(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}