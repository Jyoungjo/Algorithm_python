import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static String table;

    public static int solution() {
        int ans = 0;
        char[] cArr = table.toCharArray();

        for (int i = 0; i < N; i++) {
            boolean flag = false;
            if (cArr[i] == 'P') {
                for (int j = K; j >= 1; j--) {
                    if (i - j >= 0 && cArr[i - j] == 'H') {
                        ans++;
                        cArr[i - j] = 'X';
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    for (int j = 1; j <= K; j++) {
                        if (i + j < N && cArr[i + j] == 'H') {
                            ans++;
                            cArr[i + j] = 'X';
                            break;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        table = br.readLine();
        System.out.print(solution());
    }
}