import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static String[] words;

    public static String solution() {
        Map<String, Integer> frequentlyWords = new HashMap<>();
        for (String word : words) {
            if (word.length() >= M) {
                frequentlyWords.put(word, frequentlyWords.getOrDefault(word, 0) + 1);
            }
        }

        List<String> keySet = new ArrayList<>(frequentlyWords.keySet());
        keySet.sort((o1, o2) -> {
            if (frequentlyWords.get(o2) != frequentlyWords.get(o1)) {
                return frequentlyWords.get(o2) - frequentlyWords.get(o1);
            }

            if (o2.length() != o1.length()) {
                return o2.length() - o1.length();
            }

            return o1.compareTo(o2);
        });

        StringBuilder sb = new StringBuilder();
        for (String k : keySet) {
            sb.append(k).append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        System.out.print(solution());
    }
}