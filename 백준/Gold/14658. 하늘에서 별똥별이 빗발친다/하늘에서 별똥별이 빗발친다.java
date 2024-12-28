import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, L, K;
    static List<int[]> shootingStars = new ArrayList<>();

    public static int solution() {
        int answer = Integer.MIN_VALUE;

        for (int[] s1 : shootingStars) {
            for (int[] s2 : shootingStars) {
                answer = Math.max(answer, countBlockingStars(s1[0], s2[1]));
            }
        }

        return K - answer;
    }

    private static int countBlockingStars(int x, int y) {
        int cnt = 0;

        for (int[] s : shootingStars) {
            if (x <= s[0] && s[0] <= x + L && y <= s[1] && s[1] <= y + L) cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
            shootingStars.add(new int[]{c, r});
        }

        System.out.println(solution());
    }
}