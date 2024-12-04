import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static String[] words;

    public static int solution() {
        // 비슷한 단어를 찾아 카운팅 증가
        // 조건 1. 두 단어의 알파벳 구성, 개수가 일치
        // 조건 2. 두 단어의 알파벳 구성이 하나만 다르고 개수가 +- 1 되었을 때

        String first = words[0];
        int answer = 0;

        for (int i = 1; i < N; i++) {
            int cnt = 0;
            int[] alpha = new int[26];
            String next = words[i];

            for (int j = 0; j < first.length(); j++) {
                alpha[first.charAt(j) - 'A'] += 1;
            }

            for (int j = 0; j < next.length(); j++) {
                int idx = next.charAt(j) - 'A';

                if (alpha[idx] > 0) {
                    cnt++;
                    alpha[idx] -= 1;
                }
            }

            if (first.length() == next.length() && (cnt == next.length() || cnt == next.length() - 1)) {
                answer++;
            } else if (first.length() == next.length() - 1 && cnt == next.length() - 1) {
                answer++;
            } else if (first.length() == next.length() + 1 && cnt == next.length()) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        System.out.print(solution());
    }
}