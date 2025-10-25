import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    int N;
    int[] nums;

    private void solution() throws Exception {
        /*
            외친 숫자 짝수개 -> 중간 두 수 중 작은수
            홀수개 -> 중간값
        */
        readInput();

        Queue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> min = new PriorityQueue<>(Comparator.naturalOrder());
        for (int i = 0; i < N; i++) {
            int num = nums[i];
            if (min.size() == max.size()) max.add(num);
            else min.add(num);

            if (!min.isEmpty() && !max.isEmpty()) {
                if (min.peek() < max.peek()) {
                    int tmp = min.poll();
                    min.add(max.poll());
                    max.add(tmp);
                }
            }

            System.out.println(max.peek());
        }
    }

    private void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(br.readLine());
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}