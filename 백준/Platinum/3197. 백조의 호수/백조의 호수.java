import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    final char WATER = '.', ICE = 'X', SWAN = 'L';
    final int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};

    int R, C;
    char[][] lake;
    Queue<int[]> swanQ = new LinkedList<>();
    Queue<int[]> swanNext = new LinkedList<>();
    Queue<int[]> waterQ = new LinkedList<>();
    Queue<int[]> waterNext = new LinkedList<>();
    boolean[][] waterVisited, swanVisited;
    List<int[]> swans = new ArrayList<>();

    private void solution() throws Exception {
        readInput();
        findSwans();
        System.out.println(melt());
    }

    private int melt() {
        int turn = 0;
        init();

        while (true) {
            if (canMeet()) return turn;

            remove();
            swanQ = swanNext; swanNext = new LinkedList<>();
            waterQ = waterNext; waterNext = new LinkedList<>();
            turn++;
        }
    }

    private void init() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (lake[i][j] != ICE) waterQ.add(new int[]{i, j});
                if (lake[i][j] == SWAN) swans.add(new int[]{i, j});
            }
        }

        waterVisited = new boolean[R][C];
        swanVisited = new boolean[R][C];

        for (int[] w : waterQ) waterVisited[w[0]][w[1]] = true;
        swanQ.add(new int[]{swans.get(0)[0], swans.get(0)[1]});
        swanVisited[swans.get(0)[0]][swans.get(0)[1]] = true;
    }

    private boolean canMeet() {
        while (!swanQ.isEmpty()) {
            int[] now = swanQ.poll();

            if (now[0] == swans.get(1)[0] && now[1] == swans.get(1)[1]) return true;

            for (int d = 0; d < 4; d++) {
                int ny = now[0] + dy[d], nx = now[1] + dx[d];
                if (!isInRange(ny, nx) || swanVisited[ny][nx]) continue;

                swanVisited[ny][nx] = true;
                if (lake[ny][nx] == ICE) swanNext.add(new int[]{ny, nx});
                else swanQ.add(new int[]{ny, nx});
            }
        }

        return false;
    }

    private void remove() {
        while (!waterQ.isEmpty()) {
            int[] now = waterQ.poll();
            int y = now[0], x = now[1];

            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d], nx = x + dx[d];

                if (!isInRange(ny, nx) || waterVisited[ny][nx]) continue;

                waterVisited[ny][nx] = true;
                if (lake[ny][nx] == ICE) {
                    lake[ny][nx] = WATER;
                    waterNext.add(new int[]{ny, nx});
                } else waterQ.add(new int[]{ny, nx});
            }
        }
    }

    private boolean isInRange(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }

    private void findSwans() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (lake[i][j] == SWAN) swans.add(new int[]{i, j});
            }
        }
    }

    private void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        lake = new char[R][C];
        for (int i = 0; i < R; i++) lake[i] = br.readLine().trim().toCharArray();
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}