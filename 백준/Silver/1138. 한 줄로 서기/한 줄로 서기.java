import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] rank;

    public static String solution() {
        List<Integer> list = new LinkedList<>();

        for (int i = N - 1; i >= 0; i--) {
            list.add(rank[i], i + 1);
        }

        StringBuilder sb = new StringBuilder();
        list.forEach(e -> sb.append(e).append(" "));
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        rank = new int[N];
        for (int i = 0; i < N; i++) {
            rank[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }
}