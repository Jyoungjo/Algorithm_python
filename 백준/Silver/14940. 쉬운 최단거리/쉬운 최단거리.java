import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static int[][] result;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};

    public static String solution() {
        int[] start = findStartingPoint();

        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        result[start[0]][start[1]] = 0;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int y = current[0], x = current[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                if (0 <= ny && ny < n && 0 <= nx && nx < m && result[ny][nx] == -1) {
                    if (map[ny][nx] == 0) {
                        result[ny][nx] = 0;
                    } else {
                        q.offer(new int[]{ny, nx});
                        result[ny][nx] = result[y][x] + 1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private static int[] findStartingPoint() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{0, 0};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        result = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = -1;
                }
            }
        }

        System.out.println(solution());
    }
}