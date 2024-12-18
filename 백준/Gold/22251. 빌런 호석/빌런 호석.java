import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // N = 바뀐 층수 범위 최댓값, K = 자릿수, P = 반전 시킬 숫자 갯수, X = 현재 층수
    static int N, K, P, X;
    static int[][] pos = {
            {0, 0, 0}, {0, 1, 0}, {1, 0, 0}, {0, 0, 1}, {1, 0, 1}
    };
    static int[][][] numPos = {
            {pos[1], pos[4], pos[0], pos[4], pos[1]},
            {pos[0], pos[3], pos[0], pos[3], pos[0]},
            {pos[1], pos[3], pos[1], pos[2], pos[1]},
            {pos[1], pos[3], pos[1], pos[3], pos[1]},
            {pos[0], pos[4], pos[1], pos[3], pos[0]},
            {pos[1], pos[2], pos[1], pos[3], pos[1]},
            {pos[1], pos[2], pos[1], pos[4], pos[1]},
            {pos[1], pos[3], pos[0], pos[3], pos[0]},
            {pos[1], pos[4], pos[1], pos[4], pos[1]},
            {pos[1], pos[4], pos[1], pos[3], pos[1]},
    };
    static boolean[] visited;
    static int answer = 0;
    static int changedNum = 0;

    public static int solution() {
        int xDigit = (int) Math.log10(X) + 1;
        String target = "0".repeat(K - xDigit) + X;
        visited = new boolean[target.length()];

        dfs(0, target, 0);

        return answer;
    }

    private static void dfs(int start, String target, int cnt) {
        if (start == target.length()) {
            if (cnt >= 1 && cnt <= P && changedNum <= N) answer++;
            return;
        }

        int cur = Character.getNumericValue(target.charAt(start));

        for (int i = 0; i <= 9; i++) {
            // 여기서 dfs 들어가야할거 같음
            if (changedNum < 10 && start == target.length() - 1 && i == 0) i++;
            if (!visited[start]) {
                visited[start] = true;
                changedNum += (int) (i * Math.pow(10, target.length() - start - 1));
                if (changedNum > N) break;
                dfs(start + 1, target, cnt + changeNum(cur, i));
                visited[start] = false;
                changedNum -= (int) (i * Math.pow(10, target.length() - start - 1));
            }
        }
    }

    private static int changeNum(int cur, int i) {
        int cnt = 0;
        // 현재 자릿수의 숫자를 바꾸는 데 필요한 횟수 카운트
        for (int j = 0; j < 5; j++) {
            for (int l = 0; l < 3; l++) {
                if (numPos[cur][j][l] != numPos[i][j][l]) cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        System.out.println(solution());
    }
}