import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

class Main {
    int N, K;
    List<Integer>[][] players;
    int[][] board, pos;
    int[] dir;
    final int[] dy = {0, 0, -1, 1}, dx = {1, -1, 0, 0};

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        players = new List[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < N; j++) players[i][j] = new ArrayList<>();
        }

        pos = new int[K][2];
        dir = new int[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            players[y][x].add(i);
            pos[i][0] = y; pos[i][1] = x;
            dir[i] = d;
        }

        for (int turn = 1; turn <= 1000; turn++) {
            for (int id = 0; id < K; id++) {
                int y = pos[id][0], x = pos[id][1];
                List<Integer> from = players[y][x];
                if (from.isEmpty() || from.get(0) != id) continue;

                int d = dir[id];
                int ny = y + dy[d], nx = x + dx[d];

                if (!isInRange(ny, nx) || board[ny][nx] == 2) {
                    d = dir[id] = convertDir(d);
                    ny = y + dy[d]; nx = x + dx[d];
                    if (!isInRange(ny, nx) || board[ny][nx] == 2) continue;
                }

                List<Integer> now = new ArrayList<>(from.subList(0, from.size()));
                from.subList(0, from.size()).clear();
                
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

    private boolean isInRange(int ny, int nx) {
        return 0 <= ny && ny < N && 0 <= nx && nx < N;
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}