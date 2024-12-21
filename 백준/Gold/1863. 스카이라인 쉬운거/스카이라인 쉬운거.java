import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static List<Integer> heights;

    public static int solution() {
        Deque<Integer> deque = new ArrayDeque<>();
        int height = 0, answer = 0;

        for (int h : heights) {
            height = h;
            while (!deque.isEmpty() && deque.peek() > h) {
                if (deque.peek() != height) {
                    answer++;
                    height = deque.peek();
                }
                deque.pop();
            }
            deque.push(h);
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        heights = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Integer.parseInt(st.nextToken());
            heights.add(Integer.parseInt(st.nextToken()));
        }
        heights.add(0);

        System.out.println(solution());
    }
}