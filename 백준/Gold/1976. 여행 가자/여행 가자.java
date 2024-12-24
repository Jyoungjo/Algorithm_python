import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] cities;
    static int[] plans;
    static int[] parent;

    public static String solution() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (cities[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        
        for (int i = 1; i < M; i++) {
            if (find(plans[0]) != find(plans[i])) return "NO";
        }
        return "YES";
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            if (x > y) parent[x] = y;
            else parent[y] = x;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        else return find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        cities = new int[N + 1][N + 1];

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                cities[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        plans = new int[M];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            plans[i] = Integer.parseInt(st.nextToken());
        }

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        System.out.println(solution());
    }
}