import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static int[] conveyor;
    static boolean[] robot;

    public static int solution() {
        int answer = 0;
        while (isOk()) {
            // 1. 벨트 회전 (위에 있는 로봇도 같이 회전)
            rotateConveyor();
            // 2. 벨트 위 로봇들 이동 (로봇 이동 시 앞에 로봇 없고 내구도 1 이상)
            moveRobot();
            // 3. 올리는 위치에 로봇 올림 -> 올렸을 때 내구도 0인 벨트가 K개 라면 종료
            putRobot(0);

            answer++; // 단계 끝나면 카운트 증가
        }

        return answer;
    }

    private static void rotateConveyor() {
        int tmp = conveyor[conveyor.length - 1];
        for (int i = conveyor.length - 1; i > 0; i--) {
            conveyor[i] = conveyor[i - 1];
        }
        conveyor[0] = tmp;

        for (int i = robot.length - 1; i > 0; i--) {
            robot[i] = robot[i - 1];
        }

        // 맨 처음과 끝은 이동 후에 로봇이 존재할 수 없음
        robot[0] = false;
        robot[N - 1] = false;
    }

    private static void moveRobot() {
        for (int i = N - 1; i > 0; i--) {
            if (!robot[i] && robot[i - 1] && conveyor[i] >= 1) {
                robot[i - 1] = false;
                putRobot(i);
            }
        }
        robot[N - 1] = false;
    }

    private static void putRobot(int i) {
        if (conveyor[i] > 0) {
            robot[i] = true;
            conveyor[i]--;
        }
    }

    private static boolean isOk() {
        int cnt = 0;
        for (int c : conveyor) {
            if (c == 0) {
                cnt++;
            }

            if (cnt >= K) return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        conveyor = new int[2 * N];
        robot = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            conveyor[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }
}