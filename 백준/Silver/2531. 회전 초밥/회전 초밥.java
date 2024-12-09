import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, d, k, c;
    static int[] plates;

    public static int solution() {
        int answer, cnt = 0;
        int[] eatenSushi = new int[d + 1];

        for (int i = 0; i < k; i++) {
            if (eatenSushi[plates[i]] == 0) cnt++;
            eatenSushi[plates[i]]++;
        }

        answer = cnt;

        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, eatenSushi[c] == 0 ? cnt + 1 : cnt);

            int nextIdx = (i + k) % N;
            if (eatenSushi[plates[nextIdx]]++ == 0) cnt++;
            if (--eatenSushi[plates[i]] == 0) cnt--;
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        plates = new int[N];
        for (int i = 0; i < N; i++) {
            plates[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solution());
    }
}