import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] maze;
    static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};

    public static String solution() {
        int[] current = findPlayer();
        List<int[]> fires = findFires();
        Queue<int[]> q = new ArrayDeque<>();
        Queue<int[]> f = new ArrayDeque<>(fires);
        q.add(current);

        while (!q.isEmpty()) {
            spreadFire(f);

            int len = q.size();

            for (int a = 0; a < len; a++) {
                int[] now = q.poll();
                int y = now[0], x = now[1], time = now[2];

                if (isEdge(y, x)) return String.valueOf(time + 1);

                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i], nx = x + dx[i];

                    if (isWithinRange(nx, ny) && maze[ny][nx] == '.') {
                        q.add(new int[]{ny, nx, time + 1});
                        maze[ny][nx] = 'J';
                    }
                }

                if (maze[y][x] == 'J') maze[y][x] = 'x';
            }
        }

        return "IMPOSSIBLE";
    }

    private static boolean isEdge(int y, int x) {
        return y == R - 1 || x == C - 1 || y == 0 || x == 0;
    }

    private static void spreadFire(Queue<int[]> f) {
        int len = f.size();
        for (int i = 0; i < len; i++) {
            int[] fire = f.poll();
            int fy = fire[0], fx = fire[1];

            for (int j = 0; j < 4; j++) {
                int fyy = fy + dy[j], fxx = fx + dx[j];

                if (isWithinRange(fxx, fyy) && isSpread(fyy, fxx)) {
                    f.add(new int[]{fyy, fxx});
                    maze[fyy][fxx] = 'F';
                }
            }
        }
    }

    private static boolean isSpread(int fyy, int fxx) {
        return maze[fyy][fxx] == '.' || maze[fyy][fxx] == 'J' || maze[fyy][fxx] == 'x';
    }

    private static int[] findPlayer() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (maze[i][j] == 'J') return new int[]{i, j, 0};
            }
        }
        return null;
    }

    private static List<int[]> findFires() {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (maze[i][j] == 'F') {
                    list.add(new int[]{i, j});
                }
            }
        }
        return list;
    }

    private static boolean isWithinRange(int x, int y) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        maze = new char[R][C];
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                maze[i][j] = input.charAt(j);
            }
        }

        System.out.println(solution());
    }
}