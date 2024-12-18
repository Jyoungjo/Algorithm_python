import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static boolean[] visited;
    static List<Integer> result;

    public static String solution() {
        visited = new boolean[N + 1];
        result = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        Collections.sort(result);

        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append("\n");
        result.forEach(a -> sb.append(a).append("\n"));

        return sb.toString();
    }

    private static void dfs(int start, int target) {
        if (arr[start] == target) result.add(start);

        if (!visited[arr[start]]) {
            visited[arr[start]] = true;
            dfs(arr[start], target);
            visited[arr[start]] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        System.out.print(solution());
    }
}