import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Title {
        String titleName;
        int limit;

        public Title(String titleName, int limit) {
            this.titleName = titleName;
            this.limit = limit;
        }
    }
    static int N, M;
    static Title[] titles;

    public static String solution(int num) {
        int left = 0, right = N - 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (num <= titles[mid].limit) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return titles[left].titleName;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        titles = new Title[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            titles[i] = new Title(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(solution(num)).append("\n");
        }

        System.out.print(sb);
    }
}