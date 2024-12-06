import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, N;
    static int[] stocks, partialMax;

    public static long solution() {
        List<Integer> buying = new ArrayList<>();
        preprocessMax();

        long answer = 0;
        for (int i = 0; i < stocks.length; i++) {
            int maxVal = partialMax[i];

            if (i == stocks.length - 1) {
                answer += sellStocks(buying, maxVal);
                break;
            }

            if (stocks[i] == maxVal) {
                answer += sellStocks(buying, maxVal);
                buying.clear();
            } else {
                buying.add(stocks[i]);
            }
        }

        return answer;
    }

    private static int sellStocks(List<Integer> buying, int maxVal) {
        int result = 0;

        for (int buyingStock : buying) {
            result += maxVal - buyingStock;
        }

        return result;
    }

    private static void preprocessMax() {
        partialMax = new int[N];
        partialMax[N - 1] = stocks[N - 1];

        for (int i = N - 2; i >= 0; i--) {
            partialMax[i] = Math.max(stocks[i], partialMax[i + 1]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            stocks = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                stocks[j] = Integer.parseInt(st.nextToken());
            }

            System.out.println(solution());
        }
    }
}