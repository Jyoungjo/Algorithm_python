import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String S;

    public static String solution() {
        // 0의 개수와 1의 개수가 짝수로 존재하는 S
        // 0의 절반과 1의 절반을 지웠을 때, 사전순으로 빠른 단어 찾기 -> 0이 왼쪽에 많이 존재해야 함.
        // -> 1은 왼쪽에서부터 지우고 0은 오른쪽에서 부터 지우자.

        // 0과 1의 개수 찾기
        int zero = 0, one = 0;
        char[] cArr = S.toCharArray();

        for (char c : cArr) {
            if (c == '0') zero++;
            else one++;
        }

        // 새로운 S' 만들기
        // 1은 왼쪽에서 부터 개수의 절반 지우고
        // 0은 오른쪽에서 부터 개수의 절반 지운다.
        int zHalf = zero / 2;
        int oHalf = one / 2;

        int firstIdx = 0;
        while (oHalf > 0) {
            if (cArr[firstIdx] == '1') {
                cArr[firstIdx] = '2';
                oHalf--;
            }
            firstIdx++;
        }

        int lastIdx = S.length() - 1;
        while (zHalf > 0) {
            if (cArr[lastIdx] == '0') {
                cArr[lastIdx] = '2';
                zHalf--;
            }
            lastIdx--;
        }

        // 지운 리스트를 문자열 S'로 변환
        StringBuilder sb = new StringBuilder();
        for (char c : cArr) {
            if (c != '2') sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        System.out.println(solution());
    }
}