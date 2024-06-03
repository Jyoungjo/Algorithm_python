import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static boolean[][] arr;
    public static int min = 64;

    public static void find(int x, int y) {
        int endX = x + 8;
        int endY = y + 8;
        int cnt = 0;

        boolean TF = arr[x][y]; // 첫 번째 칸의 색

        for (int i = x; i < endX; i++) {
            for (int j = y; j < endY; j++) {
                if (arr[i][j] != TF) {
                    cnt++;
                }
                TF = !TF;
            }
            TF = !TF;
        }

        cnt = Math.min(cnt, 64 - cnt);

        min = Math.min(min, cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == 'W') {
                    arr[i][j] = true;
                } else {
                    arr[i][j] = false;
                }
            }
        }
        // 체스판이 만들어질 수 있는 가로와 세로 경우의 수
        int row = N - 7;
        int col = M - 7;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                find(i, j);
            }
        }
        System.out.print(min);
    }
}