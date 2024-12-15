import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int H, W;
    static int[] blocks;

    public static int solution() {
        int answer = 0;

        // 처음과 마지막 블록은 빗물이 고일 수 없다.
        for (int i = 1; i < W - 1; i++) {
            int left = 0, right = 0;

            for (int j = 0; j < i; j++) {
                left = Math.max(left, blocks[j]);
            }

            for (int j = i + 1; j < W; j++) {
                right = Math.max(right, blocks[j]);
            }

            if (blocks[i] < left && blocks[i] < right) {
                answer += Math.min(left, right) - blocks[i];
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        blocks = new int[W];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }
}