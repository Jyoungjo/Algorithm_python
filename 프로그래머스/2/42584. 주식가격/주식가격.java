import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Queue<Integer> queue = new LinkedList<>();

        for (int price : prices) {
            queue.add(price);
        }

        for (int i = 0; i < prices.length; i++) {
            answer[i] = 0;
        }

        int idx = 0;
        while (!queue.isEmpty()) {
            int time = 0;
            int p = queue.poll();

            for (int e : queue) {
                if (p <= e) {
                    time++;
                } else {
                    time++;
                    break;
                }
            }
            answer[idx] = time;
            idx++;
        }

        return answer;
    }
}