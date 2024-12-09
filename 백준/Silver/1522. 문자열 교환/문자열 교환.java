import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String str;

    public static int solution() {
        // 15 8 7
        int aCnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a') aCnt++;
        }

        int bCnt = 0;
        for (int i = 0; i < aCnt; i++) {
            if (str.charAt(i) == 'b') bCnt++;
        }

        int answer = bCnt;

        for (int i = 0; i < str.length(); i++) {
            int nextIdx = (i + aCnt) % str.length();
            if (str.charAt(nextIdx) == 'b') bCnt++;
            if (str.charAt(i) == 'b') bCnt--;

            answer = Math.min(answer, bCnt);
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        System.out.println(solution());
    }
}