import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] x;

    public static int solution() {
        int left = 1;
        int right = N;
        int height = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canLight(mid)) {
                right = mid - 1;
                height = mid;
            } else {
                left = mid + 1;
            }
        }

        return height;
    }

    private static boolean canLight(int height) {
        int prev = 0;

        for (int target : x) {
            if (target - height <= prev) {
                prev = target + height;
            } else {
                return false;
            }
        }

        return prev >= N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = new int[M];
        for (int i = 0; i < x.length; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }
}