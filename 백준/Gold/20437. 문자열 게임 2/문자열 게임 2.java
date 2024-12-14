import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, K;
    static String W;

    public static String solution() {
        int three = Integer.MAX_VALUE, four = Integer.MIN_VALUE;
        int[] alpha = new int[26];

        int left = 0, right = 0;
        while (left <= right) {
            if (right == W.length()) break;

            alpha[W.charAt(right) - 'a']++;

            if (alpha[W.charAt(right) - 'a'] >= K) {
                while (alpha[W.charAt(right) - 'a'] >= K) {
                    alpha[W.charAt(left++) - 'a']--;
                }
                String tmp = W.substring(--left, right + 1);
                three = Math.min(three, tmp.length());
                alpha[W.charAt(left) - 'a']++;
            }

            right++;
        }

        Map<Character, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < W.length(); i++) {
            List<Integer> val = map.getOrDefault(W.charAt(i), new ArrayList<>());
            val.add(i);
            map.put(W.charAt(i), val);
        }

        for (List<Integer> l : map.values()) {
            if (l.size() >= K) {
                for (int i = 0; i < l.size() - (K - 1); i++) {
                    four = Math.max(four, l.get(i + K - 1) - l.get(i) + 1);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (three == Integer.MAX_VALUE && four == Integer.MIN_VALUE) {
            return sb.append(-1).toString();
        }
        return sb.append(three).append(" ").append(four).toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            W = br.readLine();
            K = Integer.parseInt(br.readLine());
            System.out.println(solution());
        }
    }
}