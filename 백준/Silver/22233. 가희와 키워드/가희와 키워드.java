import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static Map<String, Integer> keywords;
    static int answer;

    public static int solution(String[] usedKeywords) {
        int cnt = 0;
        for (String usedKeyword : usedKeywords) {
            if (keywords.containsKey(usedKeyword)) {
                if (keywords.get(usedKeyword) == 0) cnt++;
                keywords.put(usedKeyword, keywords.get(usedKeyword) + 1);
            }
        }

        return answer -= cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = N;

        keywords = new HashMap<>();
        for (int i = 0; i < N; i++) {
            keywords.put(br.readLine(), 0);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String[] usedKeywords = br.readLine().split(",");
            sb.append(solution(usedKeywords)).append("\n");
        }

        System.out.print(sb);
    }
}