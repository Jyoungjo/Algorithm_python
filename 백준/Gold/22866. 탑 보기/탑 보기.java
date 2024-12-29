import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Building {
        int cnt;
        int near;

        public Building(int cnt, int near) {
            this.cnt = cnt;
            this.near = near;
        }
    }
    static int N;
    static int[] h;

    public static String solution() {
        Building[] buildings = new Building[N + 1];

        for (int i = 1; i <= N; i++) {
            buildings[i] = new Building(0, -99999);
        }

        // left
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= N; i++) {
            while (!stack.isEmpty() && h[i] >= h[stack.peek()]) stack.pop();
            if (!stack.isEmpty()) {
                buildings[i].cnt = stack.size();
                buildings[i].near = stack.peek();
            }
            stack.push(i);
        }

        stack.clear();

        for (int i = N; i >= 1; i--) {
            while (!stack.isEmpty() && h[i] >= h[stack.peek()]) stack.pop();
            if (!stack.isEmpty()) {
                buildings[i].cnt += stack.size();
                if (i - buildings[i].near > stack.peek() - i) buildings[i].near = stack.peek();
            }
            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(buildings[i].cnt);
            if (buildings[i].cnt > 0) {
                sb.append(" ").append(buildings[i].near);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }
        System.out.print(solution());
    }
}