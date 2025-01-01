import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Cctv {
        int[] coord;
        char num;

        public Cctv(int[] coord, char num) {
            this.coord = coord;
            this.num = num;
        }
    }
    static int N, M;
    static char[][] office;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int zeroCnt = 0, ans = Integer.MAX_VALUE;
    static Map<Character, int[][]> directions = new HashMap<>();


    public static int solution() {
        // 0. 방향 등록
        addDirection();
        // 1. 각 cctv 좌표 찾기
        List<Cctv> cctvList = findCctv();
        // 2. cctv 별 사각지대 탐색
        char[][] newOffice = deepCopyArr(office);
        dfs(cctvList, 0, cctvList.size(), newOffice, newOffice, 0);

        return ans;
    }

    private static char[][] deepCopyArr(char[][] original) {
        char[][] newArr = new char[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(original[i], 0, newArr[i], 0, M);
        }
        return newArr;
    }

    private static void dfs(List<Cctv> cctvList, int idx, int limit, char[][] field, char[][] saved, int cnt) {
        if (idx == limit) {
            ans = Math.min(ans, zeroCnt - cnt);
            return;
        }

        Cctv cctv = cctvList.get(idx);
        char cctvNum = cctv.num;
        int y = cctv.coord[0], x = cctv.coord[1];
        int[][] curDir = directions.get(cctvNum);
        saved = deepCopyArr(field);

        for (int i = 0; i < 4; i++) {
            // 여기서 좌표 회전해서 진행
            int tmp = 0;
            int[][] rotatedDir = cctvNum == '5' ? curDir : rotate(curDir);

            for (int[] rd : rotatedDir) {
                int ny = y + rd[0], nx = x + rd[1];
                while (isWithinRange(ny, nx) && field[ny][nx] != '6') {
                    if (field[ny][nx] == '0') {
                        field[ny][nx] = '#';
                        tmp++;
                    }
                    ny += rd[0];
                    nx += rd[1];
                }
            }

            dfs(cctvList, idx + 1, limit, field, saved, cnt += tmp);

            field = deepCopyArr(saved);
            curDir = rotatedDir;
            cnt -= tmp;
        }
    }

    private static boolean isWithinRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    private static int[][] rotate(int[][] original) {
        int[][] newDir = new int[original.length][2];

        for (int i = 0; i < original.length; i++) {
            int y = original[i][0], x = original[i][1], tmp = y;
            y = -x;
            x = tmp;
            newDir[i][0] = y;
            newDir[i][1] = x;
        }

        return newDir;
    }

    private static List<Cctv> findCctv() {
        List<Cctv> cctvList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int[] c = new int[]{i, j};
                char target = office[i][j];
                if (office[i][j] == '0') zeroCnt++;
                if (office[i][j] != '0' && office[i][j] != '6') {
                    cctvList.add(new Cctv(c, target));
                }
            }
        }

        return cctvList;
    }

    private static void addDirection() {
        directions.put('1', new int[][]{dir[0]});
        directions.put('2', new int[][]{dir[0], dir[2]});
        directions.put('3', new int[][]{dir[0], dir[3]});
        directions.put('4', new int[][]{dir[0], dir[2], dir[3]});
        directions.put('5', new int[][]{dir[0], dir[1], dir[2], dir[3]});
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        office = new char[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                office[i][j] = st.nextToken().charAt(0);
            }
        }

        System.out.println(solution());
    }
}