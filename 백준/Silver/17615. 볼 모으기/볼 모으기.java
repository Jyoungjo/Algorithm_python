import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static String balls;

    public static int solution() {
        return Math.min(moveBall('R'), moveBall('B'));
    }

    private static int moveBall(char color) {
        int lCnt = 0, rCnt = 0;
        boolean isFind = false;
        for (int i = 0; i < N; i++) {
            if (balls.charAt(i) != color) isFind = true;
            else if (isFind) lCnt++;
        }

        isFind = false;
        for (int i = balls.length() - 1; i >= 0; i--) {
            if (balls.charAt(i) != color) isFind = true;
            else if (isFind) rCnt++;
        }

        return Math.min(lCnt, rCnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        balls = br.readLine();
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());

        System.out.println(solution());
    }
}