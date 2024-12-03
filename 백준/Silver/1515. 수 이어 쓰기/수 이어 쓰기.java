import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String N;

    public static int solution() {
        int base = 0, idx = 0;

        while (base++ <= 30000) {
            String target = String.valueOf(base);
            for (int i = 0; i < target.length(); i++) {
                if (target.charAt(i) == N.charAt(idx)) {
                    idx++;
                }

                if (idx == N.length()) {
                    return base;
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();
        System.out.print(solution());
    }
}