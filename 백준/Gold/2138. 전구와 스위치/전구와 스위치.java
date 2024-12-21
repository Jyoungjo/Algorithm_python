import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static String before, after;

    public static int solution() {
        // 최소 횟수로 전구 스위치를 눌러 만들고자 하는 모양 만들기
        // 그리디 문제 -> 순회하면서 현재 위치 앞에 있는 전구에 따라 현재 위치의 전구 상태 변경
        // 첫번째 전구의 경우 0번째 전구의 값을 참조하지 못하기 때문에 킨 경우와 안 킨 경우 나눠서 계산

        char[] turnOnZero = before.toCharArray();
        char[] turnOffZero = before.toCharArray();

        for (int i = 0; i <= 1; i++) {
            turnOnZero[i] = turnOnZero[i] == '0' ? '1' : '0';
        }

        int cntOn = 1;
        int cntOff = 0;

        for (int i = 1; i < N; i++) {
            if (turnOnZero[i - 1] != after.charAt(i - 1)) {
                cntOn++;
                for (int j = i - 1; j <= i + 1; j++) {
                    if (i == N - 1 && j == i + 1) break;
                    turnOnZero[j] = turnOnZero[j] == '0' ? '1' : '0';
                }
            }
            if (turnOffZero[i - 1] != after.charAt(i - 1)) {
                cntOff++;
                for (int j = i - 1; j <= i + 1; j++) {
                    if (i == N - 1 && j == i + 1) break;
                    turnOffZero[j] = turnOffZero[j] == '0' ? '1' : '0';
                }
            }
        }

        String changedOn = String.valueOf(turnOnZero);
        String changedOff = String.valueOf(turnOffZero);

        if (changedOn.equals(after) && changedOff.equals(after)) {
            return Math.min(cntOff, cntOn);
        } else if (changedOn.equals(after)) {
            return cntOn;
        } else if (changedOff.equals(after)) {
            return cntOff;
        } else return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        before = br.readLine();
        after = br.readLine();

        System.out.println(solution());
    }
}