import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, a, b;
    static String IMPOSSIBLE = "-1";

    public static String solution() {
        if (N + 1 < a + b) return IMPOSSIBLE;
        
        List<Integer> towers = new ArrayList<>();
        int maxH = Math.max(a, b);

        for (int i = 1; i < a; i++) towers.add(i);
        towers.add(maxH);
        for (int i = b - 1; i >= 1; i--) towers.add(i);
        
        if (a == 1) while (towers.size() < N) towers.add(1, 1);
        else while (towers.size() < N) towers.add(0, 1);
        
        StringBuilder sb = new StringBuilder();
        towers.forEach(a -> sb.append(a).append(" "));
        
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        System.out.println(solution());
    }
}