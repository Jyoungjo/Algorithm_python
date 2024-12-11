import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String S, T;

    public static int solution() {
        Queue<String> q = new LinkedList<>();
        Set<String> set = new HashSet<>();
        q.offer(T);

        while (!q.isEmpty()) {
            String current = q.poll();
            
            if (current.equals(S)) return 1;
            if (current.length() <= S.length()) continue;

            if (current.charAt(current.length() - 1) == 'A') {
                String s1 = current.substring(0, current.length() - 1);
                addQueue(set, s1, q);
            }

            if (current.charAt(0) == 'B') {
                StringBuilder sb = new StringBuilder(current);
                String s2 = sb.deleteCharAt(0).reverse().toString();
                addQueue(set, s2, q);
            }
        }

        return 0;
    }

    private static void addQueue(Set<String> set, String s1, Queue<String> q) {
        if (!set.contains(s1)) {
            set.add(s1);
            q.offer(s1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = br.readLine();

        System.out.println(solution());
    }
}