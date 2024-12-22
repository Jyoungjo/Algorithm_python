import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] board;
    static boolean[] alpha = new boolean[26];
    static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    static int answer = 0;

    public static int solution() {
        dfs(0, 0, 1);

        return answer;
    }

    private static void dfs(int y, int x, int len) {
        alpha[board[y][x]] = true;
        answer = Math.max(answer, len);

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i], nx = x + dx[i];

            if (0 <= ny && ny < R && 0 <= nx && nx < C && !alpha[board[ny][nx]]) {
                dfs(ny, nx, len + 1);
                alpha[board[ny][nx]] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new int[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = str.charAt(j) - 'A';
            }
        }

        System.out.println(solution());
    }
}