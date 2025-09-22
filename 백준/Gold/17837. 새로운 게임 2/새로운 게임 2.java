import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    final int[] dy = {0, 0, -1, 1}, dx = {1, -1, 0, 0};
    int N, K;
    int[][] board, pos;
    int[] dir;
    List<Integer>[][] players;

    private void solution() throws IOException {
        readInput();
        play();
    }

    private void play() {
        for (int turn = 1; turn <= 1000; turn++) {
            for (int id = 0; id < K; id++) {
                int y = pos[id][0], x = pos[id][1];
                List<Integer> from = players[y][x];

                int d = dir[id];
                int ny = y + dy[d], nx = x + dx[d];

                if (!isInRange(ny, nx) || board[ny][nx] == 2) {
                    d = dir[id] = convertDir(d);
                    ny = y + dy[d]; nx = x + dx[d];
                    if (!isInRange(ny, nx) || board[ny][nx] == 2) continue;
                }

                List<Integer> now = new ArrayList<>(from.subList(from.indexOf(id), from.size()));
                from.subList(from.indexOf(id), from.size()).clear();

                if (board[ny][nx] == 1) Collections.reverse(now);
                List<Integer> to = players[ny][nx];

                for (int p : now) {
                    to.add(p);
                    pos[p][0] = ny;
                    pos[p][1] = nx;
                }

                if (to.size() >= 4) {
                    System.out.println(turn);
                    return;
                }
            }
        }

        System.out.println(-1);
    }

    private int convertDir(int dir) {
        return switch (dir) {
            case 0 -> 1;
            case 1 -> 0;
            case 2 -> 3;
            case 3 -> 2;
            default -> dir;
        };
    }

    private boolean isInRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][];
        pos = new int[K][2];
        dir = new int[K];
        players = new List[N][N];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < N; j++) players[i][j] = new ArrayList<>();
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            players[y - 1][x - 1].add(i);
            pos[i][0] = y - 1; pos[i][1] = x - 1;
            dir[i] = d - 1;
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}