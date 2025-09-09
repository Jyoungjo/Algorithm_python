import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    char[] T, P;
    int[] pi;

    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = br.readLine().toCharArray();
        P = br.readLine().toCharArray();
        makePi();

        StringBuilder sb = new StringBuilder();
        int pIdx = 0, matchCnt = 0;
        for (int wIdx = 0; wIdx < T.length; wIdx++) {
            while (pIdx > 0 && T[wIdx] != P[pIdx]) pIdx = pi[pIdx - 1];
            
            if (T[wIdx] == P[pIdx]) {
                if (pIdx == P.length - 1) {
                    matchCnt++;
                    sb.append(wIdx - pIdx + 1).append(" ");
                    pIdx = pi[pIdx];
                } else pIdx++;
            }
        }

        System.out.println(matchCnt);
        System.out.println(sb);
    }

    private void makePi() {
        pi = new int[P.length];
        int j = 0;
        for (int i = 1; i < P.length; i++) {
            while (j > 0 && P[i] != P[j]) j = pi[j - 1];
            if (P[i] == P[j]) pi[i] = ++j;
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}