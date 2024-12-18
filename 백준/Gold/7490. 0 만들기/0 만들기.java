import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T;
    static List<String> answer;
    static Deque<Integer> numbers;
    static StringBuilder sb;

    public static String solution(int N) {
        numbers.add(1);
        sb.append(1);
        dfs(1, N);

        sb = new StringBuilder();
        Collections.sort(answer);
        answer.forEach(a -> sb.append(a).append("\n"));
        return sb.toString();
    }

    private static void dfs(int now, int N) {
        if (now == N) {
            if (numbers.stream().reduce(0, Integer::sum) == 0) answer.add(sb.toString());
            return;
        }

        int next = now + 1;
        int tmp = numbers.peekLast();
        char[] symbol = {'+', '-', ' '};
        int[] cal = {next, -next, tmp > 0 ? tmp * 10 + next : tmp * 10 - next};

        for (int i = 0; i < 3; i++) {
            if (i == 2) numbers.pollLast();
            numbers.add(cal[i]);
            sb.append(symbol[i]).append(next);
            dfs(next, N);
            numbers.pollLast();
            if (i == 2) numbers.addLast(tmp);
            sb.delete(sb.length() - 2, sb.length());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            sb = new StringBuilder();
            answer = new ArrayList<>();
            numbers = new ArrayDeque<>();

            System.out.println(solution(Integer.parseInt(br.readLine())));
        }
    }
}