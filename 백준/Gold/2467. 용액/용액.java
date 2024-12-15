import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] solutions;

    public static String solution() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o2[2] - o1[2]));
        StringBuilder sb = new StringBuilder();
        int left = 0, right = N - 1;
        int tmp = Integer.MAX_VALUE;

        while (left < right) {
            int sum = solutions[left] + solutions[right];

            if (sum == 0) {
                return sb.append(solutions[left]).append(" ").append(solutions[right]).toString();
            } else if (sum < 0){
                if (tmp >= Math.abs(sum)) {
                    pq.poll();
                    tmp = Math.abs(sum);
                    pq.add(new int[]{left, right, tmp});
                }
                left++;
            } else {
                if (tmp >= Math.abs(sum)) {
                    pq.poll();
                    tmp = Math.abs(sum);
                    pq.add(new int[]{left, right, tmp});
                }
                right--;
            }
        }

        int[] result = pq.poll();
        sb.append(solutions[result[0]]).append(" ").append(solutions[result[1]]);

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        solutions = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }
}