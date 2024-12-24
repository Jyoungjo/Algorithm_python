import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] buildings;

    public static int solution() {
        // 고층 빌딩이 가장 많이 보이는 개수 구하기
        // A와 B 빌딩 사이의 기울기가 한 빌딩을 기준으로 왼쪽 감소, 오른쪽 증가하는 형태를 탐색한다.
        // 기울기 = (y2 - y1) / (x2 - x1)
        int answer = 0;

        if (buildings.length == 1) return 0;

        for (int k = 0; k < N; k++) {
            int cnt = 0;
            float tmp = 0;

            // 빌딩 k 기준 왼쪽 탐색
            for (int i = k - 1; i >= 0; i--) {
                float slope = (float) (buildings[k] - buildings[i]) / (k - i);
                if (i == k - 1 || tmp > slope) {
                    cnt++;
                    tmp = slope;
                }
            }

            // 빌딩 k 기준 오른쪽 탐색
            for (int i = k + 1; i < N; i++) {
                float slope = (float) (buildings[i] - buildings[k]) / (i - k);

                if (i == k + 1 || tmp < slope) {
                    cnt++;
                    tmp = slope;
                }
            }

            answer = Math.max(answer, cnt);
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        buildings = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }
}