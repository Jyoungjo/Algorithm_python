import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static String[] options;

    public static String solution() {
        boolean[] alpha = new boolean[26];

        StringBuilder sb;
        for (int i = 0; i < N; i++) {
            String[] words = options[i].split(" ");
            sb = new StringBuilder();
            boolean flag = false;

            // 단어 첫글자 단축키 지정되었는지 확인
            for (String word : words) {
                String tmp = word;
                char first = word.charAt(0);
                int idx = 0 <= first - 'a' && first - 'a' < 26 ? first - 'a' : first - 'A';

                if (!alpha[idx] && !flag) {
                    alpha[idx] = true;
                    flag = true;
                    tmp = String.format("[%s]%s", word.charAt(0), word.substring(1));
                }

                sb.append(tmp).append(" ");
            }

            options[i] = sb.toString();

            // 만약 모든 단어의 첫 글자가 단축키 지정이 되어있다면 해당 옵션 순회하면서 겹치지 않는 제일 빠른 인덱스에 단축키 지정
            if (!flag) {
                sb = new StringBuilder();
                String tmp = options[i];

                for (int j = 0; j < options[i].length(); j++) {
                    char c = options[i].charAt(j);
                    if (c == ' ') continue;

                    int idx = 0 <= c - 'a' && c - 'a' < 26 ? c - 'a' : c - 'A';
                    if (!alpha[idx]) {
                        alpha[idx] = true;
                        String a = j == options[i].length() ? "" : options[i].substring(j + 1);
                        tmp = String.format("%s[%s]%s", options[i].substring(0, j), options[i].charAt(j), a);
                        break;
                    }
                }
                sb.append(tmp);
                options[i] = sb.toString();
            }
        }

        StringBuilder result = new StringBuilder();
        Arrays.stream(options).forEach(a -> result.append(a).append("\n"));
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        options = new String[N];

        for (int i = 0; i < N; i++) {
            options[i] = br.readLine();
        }

        System.out.println(solution());
    }
}