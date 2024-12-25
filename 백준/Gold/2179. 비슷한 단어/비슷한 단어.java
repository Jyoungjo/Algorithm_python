import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static String[] words;

    public static String solution() {
        // 접두사의 길이가 최대인 비슷한 단어 찾기
        // ABC, ABCDEF -> 접두사 = ABC
        int fIdx = -1, sIdx = -1;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N - 1; i++) {
            String target = words[i];
            for (int j = i + 1; j < N; j++) {
                String tmp = words[j];

                int cnt = countSameChar(target, tmp);

                if (cnt > max) {
                    fIdx = i;
                    sIdx = j;
                    max = cnt;
                }
            }
        }

        return words[fIdx] + "\n" + words[sIdx];
    }

    private static int countSameChar(String s1, String s2) {
        int cnt = 0, len = Math.min(s1.length(), s2.length());

        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) == s2.charAt(i)) cnt++;
            else break;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        System.out.println(solution());
    }
}